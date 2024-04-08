package fr.mowitnow.lawnmower.config;

import fr.mowitnow.lawnmower.model.Mower;
import fr.mowitnow.lawnmower.processor.MowerProcessor;
import fr.mowitnow.lawnmower.reader.InputReader;
import fr.mowitnow.lawnmower.writer.OutputWriter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.PlatformTransactionManager;


@Configuration
@RequiredArgsConstructor
@Slf4j
public class BatchConfig {

  private final JobRepository jobRepository;

  private final PlatformTransactionManager transactionManager;

  @Autowired
  private JobLauncher jobLauncher;


  @Scheduled(cron = "${application.scheduler.mower.cron}")
  public void perform() {
    try {
      var param = new JobParametersBuilder().addLong("time", System.currentTimeMillis()).toJobParameters();
      jobLauncher.run(mowerJob(), param);
    } catch (Exception e) {
      log.error("Job mowerJob finished with status  Failed" + e.getClass().getName() + " " +
          e.getMessage());
    }
  }


  @Bean
  public Job mowerJob() {
    return new JobBuilder("mowerJob", jobRepository)
        .incrementer(new RunIdIncrementer())
        .start(mowerStep())
        .build();
  }

  @Bean
  public Step mowerStep() {
    return new StepBuilder("mowerStep", jobRepository)
        .<Mower, Mower>chunk(10, transactionManager)
        .reader(inputReader())
        .processor(processor())
        .writer(outputWriter())
        .build();
  }

  @Bean
  public ItemReader<Mower> inputReader() {
      return InputReader.createFlatFileItemReader();
  }

  @Bean
  public ItemProcessor<Mower, Mower> processor() {
      return new MowerProcessor();
  }


  @Bean
  public ItemWriter<Mower> outputWriter() {
      return new OutputWriter();
  }
}

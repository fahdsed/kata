package fr.mowitnow.lawnmower.batch;

import static org.assertj.core.api.Assertions.assertThat;

import fr.mowitnow.lawnmower.config.BatchConfig;
import fr.mowitnow.lawnmower.model.Mower;
import fr.mowitnow.lawnmower.processor.MowerProcessor;
import fr.mowitnow.lawnmower.reader.InputReader;
import fr.mowitnow.lawnmower.writer.OutputWriter;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest(classes = BatchConfig.class)
class BatchConfigTest {

  @Autowired
  private BatchConfig batchConfig;

  @MockBean
  private JobLauncherTestUtils jobLauncherTestUtils;

  @Test
  void testMowerJobBean() {
    Job mowerJob = batchConfig.mowerJob();
    assertThat(mowerJob).isNotNull();
    assertThat(mowerJob.getName()).isEqualTo("mowerJob");
  }

  @Test
  void testMowerStepBean() {
    Step mowerStep = batchConfig.mowerStep();
    assertThat(mowerStep).isNotNull();
    assertThat(mowerStep.getName()).isEqualTo("mowerStep");
  }

  @Test
  void testInputReaderBean() {
    ItemReader<Mower> inputReader = batchConfig.inputReader();
    assertThat(inputReader).isNotNull().isInstanceOf(InputReader.class);
  }

  @Test
  void testProcessorBean() {
    ItemProcessor<Mower, Mower> processor = batchConfig.processor();
    assertThat(processor).isNotNull().isInstanceOf(MowerProcessor.class);
  }

  @Test
  void testOutputWriterBean() {
    ItemWriter<Mower> outputWriter = batchConfig.outputWriter();
    assertThat(outputWriter).isNotNull().isInstanceOf(OutputWriter.class);
  }
}

package org.test.alfa;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.parsers.RegexPrefixCapturingPatternParser;
import org.jbehave.core.reporters.CrossReference;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.jbehave.core.steps.PrintStreamStepMonitor;
import org.testng.annotations.Test;

import java.util.List;

import static org.jbehave.core.io.CodeLocations.codeLocationFromClass;
import static org.jbehave.core.reporters.StoryReporterBuilder.Format.*;

/**
 * Created by Vladimir on 05.12.2016.
 */

public class JBehaveRunnerAlfaTV extends JUnitStories {
    public JBehaveRunnerAlfaTV() {
        super();
        this.configuredEmbedder().candidateSteps().add(new AlfaTestStepsTV());
    }
    private final CrossReference xref = new CrossReference();

    //    @Override
    public Configuration configuration = new MostUsefulConfiguration()
            .useStoryLoader(new LoadFromClasspath(this.getClass()))
            .useStoryReporterBuilder(
                    new StoryReporterBuilder()
                            .withFormats(STATS, CONSOLE, TXT, HTML, XML)
                            .withFailureTrace(true).withCrossReference(xref))
            .useStepPatternParser(new RegexPrefixCapturingPatternParser("%"))
            .useStepMonitor(new PrintStreamStepMonitor());

    @Override
    public InjectableStepsFactory stepsFactory() {
        return new InstanceStepsFactory(configuration, new AlfaTestStepsTV());
    }

    @Test
    protected List<String> storyPaths() {
        return new StoryFinder().findPaths(codeLocationFromClass(this.getClass()),
                "**/AlfaTV.story", "");
    }
}
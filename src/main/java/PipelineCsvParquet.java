import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.transforms.Count;
import org.apache.beam.sdk.transforms.FlatMapElements;
import org.apache.beam.sdk.values.PCollection;
import org.apache.beam.sdk.values.TypeDescriptors;

import java.util.Arrays;

public class PipelineCsvParquet {

    private static final String DEFAULT_CONFIG_FILE = "application1.properties";
    public static void main(String args[]) {

        System.out.println("Hello World");

        PipelineCsvParquet sp = new PipelineCsvParquet();
        String propFile = null;

        if(args.length > 0) // For custom properties file
            propFile = args[0];
        else
            propFile = DEFAULT_CONFIG_FILE;
        //sp.init(propFile);

        sp.run();

    }

    public void run()
    {
        //create Pipeline with options
        Pipeline pipeline = Pipeline.create();
        doDataProcessing(pipeline);
        pipeline.run();
    }

    public void doDataProcessing(Pipeline pipeline){
    {
        PCollection<String> lines = pipeline.apply(TextIO.read()
                .from("C:\\Users\\shumondal\\Downloads\\hello\\src\\main\\resources\\test.csv"))
                .apply("ExtractWords", FlatMapElements
                        .into(TypeDescriptors.strings())
                        .via((String word) -> Arrays.asList(word.split("[^\\p{L}]+"))));


        System.out.println(lines);


    }
}
}

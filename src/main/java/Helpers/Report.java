package Helpers;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

public class Report {

    public static void main(String args[]){
        GenerateMasterthoughtReport();
    }
    public static void GenerateMasterthoughtReport( )
    {
        try
        {
            File reportOutputDirectory = new File("target/Report");
            File dir = new File("target/cucumber-parallel");
            File[] txts = dir.listFiles(new FilenameFilter()
            {
                public boolean accept(File dir, String name)
                {
                    if(name.endsWith(".json"))
                        return true;
                    return false;
                }
            });
            List< String > list = new ArrayList<String>();
            for(File path:txts)
            {
                list.add(path.toString());
            }
            String buildProject = "TodoMVC";
            Configuration configuration = new Configuration(reportOutputDirectory, buildProject);
            ReportBuilder reportBuilder = new ReportBuilder(list, configuration);
            reportBuilder.generateReports();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}

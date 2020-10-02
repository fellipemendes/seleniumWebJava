package com.common.utils.RunnerClass;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.CodeSource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.common.utils.folder.FileManagerUtils;
import com.reports.ReportJson;
import com.steps.common.Hooks;


public class Runner {
	public static String JarFileName = "";
	public static void main(String[] args) throws IOException, URISyntaxException {
		CodeSource codeSource = Runner.class.getProtectionDomain().getCodeSource();
		File jarFile = new File(codeSource.getLocation().toURI().getPath());
		JarFileName = jarFile.getParentFile().getAbsolutePath();

		String TEST_FOLDER = "com/features/";
		String TEMP_FOLDER = "features";
		String[] FEATURES = {	"file.feature"};

		String[] tags = {"~@ignore"};

		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		Date date = new Date();
		String reportDirName = "Run";
		String reportRoot = "cucumber-html-reports/";
		String reportDir = reportRoot + reportDirName + "/";
		reportDirName = reportDirName.replace(" ", "");
		reportDir = reportDir.replace(" ", "");

		FileManagerUtils fUtils = new FileManagerUtils();

		BasicConfigurator.configure();

		if ((new File(TEMP_FOLDER).exists()))
			fUtils.tempDirectoryDelete(TEMP_FOLDER);
		if ((new File(reportRoot).exists()))
			fUtils.tempDirectoryDelete(reportRoot);


		fUtils.tempFeatureCreate(TEMP_FOLDER, TEST_FOLDER, FEATURES);

		String[] glue = {"com.steps"};

		String[] plugins = { "pretty", "json:" + reportDir + reportDirName + ".json" };
		String[] arguments = {"-s", "-m", "-p", plugins[0], "-p", plugins[1], "-g", glue[0], TEMP_FOLDER};

		if ((args.length != 0) || (tags.length != 0)) {

			for (String arg : args) {
				tags = ArrayUtils.add(tags, arg);
			}

			for(String tag:tags) {
				arguments = ArrayUtils.add(arguments, "-t");
				arguments = ArrayUtils.add(arguments, tag);
			}
		}

		try {
			io.cucumber.core.cli.Main.run(arguments, Thread.currentThread().getContextClassLoader());
		} catch (Throwable e) {
			e.printStackTrace();
		}


		ReportJson.generateFinalReport(reportDir, reportDir, "Report " + reportDirName);

		// abrir o relatorio no final
		String reportAbsPath = new File(reportDir).getAbsolutePath();
		String report = reportAbsPath + "/cucumber-html-reports/overview-features.html";

		System.out.println("REPORT GENERATED: ");
		System.out.println(report);

		fUtils.tempDirectoryDelete(TEMP_FOLDER);
	}

}
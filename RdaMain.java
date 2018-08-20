package rda;

import java.util.Iterator;
import java.util.List;
import soot.*;
import soot.Body;
import soot.NormalUnitPrinter;
import soot.Scene;
import soot.SootClass;
import soot.SootMethod;
import soot.Unit;
import soot.UnitPrinter;
import soot.toolkits.graph.ExceptionalUnitGraph;
import soot.toolkits.graph.UnitGraph;
import soot.jimple.internal.*;
import java.util.*;
import java.io.*; 
public class RdaMain
{
	public static void main(String[] args)   {
		
		if (args.length < 2) {
			System.out.println("Usage: java lva.LvaMain class_to_analyse class_path");
			System.exit(1);
		} else {
			System.out.println("Analyzing class: "+args[0]);
		}

		String mainClass = args[0];


		String classPath = args[1]; // 此处的路径是你待分析的类所在的路径
		

		//Set up arguments for Soot
		String[] sootArgs = {
			"-cp", classPath, "-pp",
			"-w", 						// 执行整个程序分析
			"-src-prec", "java",		// 指定源文件类型
			"-main-class", mainClass,	// 指定主类 
			"-f", "J", 					// 指定输出文件类型
			mainClass 
		};

		AnalysisTransformer analysisTransformer = new AnalysisTransformer();
		PackManager.v().getPack("wjtp").add(new Transform("wjtp.dfa", analysisTransformer));

		// 调用sootMain
		soot.Main.main(sootArgs);

	}
}

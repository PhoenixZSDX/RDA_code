package rda;

import java.util.*;
import soot.*;
import soot.toolkits.graph.*;
import soot.toolkits.scalar.BackwardFlowAnalysis;
import soot.toolkits.scalar.FlowSet;

import java.io.FileInputStream;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.io.InputStream;  
import java.util.Iterator; 
import org.apache.poi.xssf.usermodel.XSSFCell;  
import org.apache.poi.xssf.usermodel.XSSFRow;  
import org.apache.poi.xssf.usermodel.XSSFSheet;  
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class AnalysisTransformer extends SceneTransformer   
{

	@Override
	protected void internalTransform(String arg0, Map arg1)   {

		// 我们首先获取Main方法，因为我们的分析应当从Main方法开始
		SootMethod sMethod = Scene.v().getMainMethod();

		// 获取当前Main方法中ActiveBody
		// ActiveBody: The body of a method contains the statements inside that method as well as 
		// the `local variable` definitions and the exception handlers.
		UnitGraph graph = new BriefUnitGraph(sMethod.getActiveBody());

		// 执行活跃变量分析
		ReachingDefinitionAnalysis analysis = new ReachingDefinitionAnalysis(graph);

		
		Iterator<Unit> unitIt = graph.iterator();


		int rowIndex = 0;
	
		XSSFWorkbook wb = new XSSFWorkbook();  
		XSSFSheet sheet = wb.createSheet("sheet1");
		XSSFRow row = sheet.createRow(rowIndex++);
        row.createCell(0).setCellValue("Basic Block");
        row.createCell(1).setCellValue("入口处reaching definitions");
        row.createCell(2).setCellValue("出口处reaching definitions");
		while (unitIt.hasNext()) {
			Unit s = unitIt.next();
			row = sheet.createRow(rowIndex++);
			String entryVals = "";
			String exitVals = "";
			row.createCell(0).setCellValue(s.toString());

			FlowSet<Local> set = analysis.getFlowBefore(s);

			for (Local local: set) {
				entryVals += local + " ";
			}

			set = analysis.getFlowAfter(s);
			
			System.out.print("]\t[exit: ");
			for (Local local: set) {
				exitVals += local + " ";
			}
            row.createCell(1).setCellValue(entryVals);
            row.createCell(2).setCellValue(exitVals);
		}
		try {
			FileOutputStream fileOut = new FileOutputStream("./result.xlsx");  
			// write this workbook to an Outputstream.  
			wb.write(fileOut);  
			fileOut.flush();  
			fileOut.close();  
		}
		catch (IOException ex) {
			System.out.print("IO exception occurred");
		}
		
	}
}

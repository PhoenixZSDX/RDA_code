import java.awt.BorderLayout;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
 
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import javafx.geometry.VerticalDirection;

import javax.swing.JFrame;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.*;
import java.awt.Color;
import java.io.File;
import java.io.BufferedReader;  
import java.io.*;
//import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RdaUI extends JFrame implements ActionListener {
    JFrame f1 = new JFrame();
    JFrame f2 = new JFrame();
    //JButton open_button = new JButton("打开文件");
    JButton excel_button = new JButton("getExcel");
    JButton cfg_button = new JButton("getCFG");
    JButton back_button = new JButton("go back");
    JLabel label = new JLabel("基于soot平台的定值到达分析", JLabel.CENTER);
    JLabel result = new JLabel("", JLabel.CENTER);
    JTextArea codeText;
    JSplitPane jSplitPane;
    String classPath;
    String mainClass;
    public static void main(String[] args) {
        new RdaUI();
    }
    public RdaUI() {
        f1.setTitle("定值到达分析");
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JMenuBar menuBar = new JMenuBar();
        f1.setJMenuBar(menuBar);
        JMenu menu = new JMenu("文件");// 创建菜单对象
        menu.setMnemonic(KeyEvent.VK_F);
        //menu.setMnemonic('F');
		menuBar.add(menu);// 将菜单对象添加到菜单栏对象中
		menuBar.setFocusable(true);
		
		JMenuItem menuItem = new JMenuItem("打开(O)");// 创建菜单项对象
		menuItem.setActionCommand("open_file");
		menuItem.addActionListener(this);
		menuItem.setMnemonic('O');
		menu.add(menuItem);// 将菜单项对象添加到菜单对象中
		
//		JMenu sonMenu = new JMenu("子菜单名称");// 创建菜单的子菜单对象
//		menu.add(sonMenu);// 将子菜单对象添加到上级菜单对象中
//		
//		JMenuItem sonMenuItem = new JMenuItem("子菜单项名称");// 创建子菜单的菜单项对象
//		sonMenuItem.addActionListener(new ItemListener());// 为菜单项添加事件监听器
//		sonMenu.add(sonMenuItem);// 将子菜单的菜单项对象添加到子菜单对象中
		
        
        f1.getContentPane().add(label, BorderLayout.CENTER);
        
        //open_button.setActionCommand("open_file");
        //open_button.setBackground(Color.BLUE);
        //f1.getContentPane().add(open_button, BorderLayout.SOUTH);
        //open_button.addActionListener(this);
        
        f1.setSize(400, 300);
        f1.setLocation(200, 200);
        f1.setVisible(true);
        
        f2.setTitle("定值到达分析");
        f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        codeText = new JTextArea();
        codeText.setEditable(false);
        JPanel p1 = new JPanel();
        p1.setLayout(new BorderLayout());
        p1.add(new JScrollPane(codeText), BorderLayout.CENTER);
        p1.setFocusable(false);
        
        JMenuBar menuBar2 = new JMenuBar();
        f2.setJMenuBar(menuBar2);
        JMenu menu2 = new JMenu("操作");// 创建菜单对象
        menu.setMnemonic(KeyEvent.VK_O);//设置快捷键
		menuBar2.add(menu2);// 将菜单对象添加到菜单栏对象中
		JMenu sonMenu = new JMenu("分析(A)");// 创建菜单的子菜单对象
		sonMenu.setMnemonic('A');//设置快捷键
		menu2.add(sonMenu);// 将子菜单对象添加到上级菜单对象中
		
		JMenuItem sonMenuItem1 = new JMenuItem("获得Excel(E)");// 创建子菜单的菜单项对象
		sonMenuItem1.setActionCommand("getExcel");
		sonMenuItem1.addActionListener(this);// 为菜单项添加事件监听器
		sonMenuItem1.setMnemonic('E');//设置快捷键
		sonMenu.add(sonMenuItem1);// 将子菜单的菜单项对象添加到子菜单对象中
		
		JMenuItem sonMenuItem2 = new JMenuItem("获得CFG图(C)");// 创建子菜单的菜单项对象
		sonMenuItem2.setActionCommand("getCFG");
		sonMenuItem2.addActionListener(this);// 为菜单项添加事件监听器
		sonMenuItem2.setMnemonic('C');//设置快捷键
		sonMenu.add(sonMenuItem2);// 将子菜单的菜单项对象添加到子菜单对象中
		
		
        JPanel p2 = new JPanel();
//        excel_button.setActionCommand("getExcel");
//        excel_button.setBackground(Color.YELLOW);
//        p2.add(excel_button);
//        excel_button.addActionListener(this);
//        cfg_button.setActionCommand("getCFG");
//        cfg_button.setBackground(Color.GREEN);
//        p2.add(cfg_button);
//        cfg_button.addActionListener(this);
//        back_button.setActionCommand("goBack");
//        back_button.setBackground(Color.BLUE);
//        p2.add(back_button);
//        back_button.addActionListener(this);
        p2.add(result);
        
        jSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        jSplitPane.setTopComponent(p1);
        jSplitPane.setBottomComponent(p2);
        jSplitPane.setDividerLocation(680);
        
        f2.setContentPane(jSplitPane);
        f2.setSize(480, 768);
        f2.setLocation(400, 200);
        //f2.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equals("open_file")) {
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.setCurrentDirectory(new File("."));
            FileNameExtensionFilter filter = new FileNameExtensionFilter("class文件(*.class)", "class");
            jFileChooser.setFileFilter(filter);
            jFileChooser.showOpenDialog(this);
            File file = jFileChooser.getSelectedFile();
            String path = file.getAbsolutePath();
            String os = System.getProperty("os.name").toLowerCase();
            if (os.startsWith("win")) {
                classPath = path.substring(0, path.lastIndexOf("\\"));
            } else {
                classPath = path.substring(0, path.lastIndexOf("/"));
                //classPath = path.substring(path.indexOf("."), path.lastIndexOf("/"));
            }
            mainClass = file.getName();
            mainClass = mainClass.substring(0, mainClass.lastIndexOf("."));
            f1.setVisible(false);
            showCodeText();
            f2.setVisible(true);
        }
        else if (ae.getActionCommand().equals("getExcel")) {
            Runtime runtime = Runtime.getRuntime();
            String command = "java rda.RdaMain " + mainClass + " " + classPath;
            result.setText("Get Excel Successfully!");
            try {
                Process process = runtime.exec(command);
                if (process.waitFor() != 0) {
                    if (process.exitValue() == 1) {
                        result.setText("Failure!");
                        System.err.println("指令执行失败!");
                    } else {
                        result.setText("Get Excel Successfully!");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            String os = System.getProperty("os.name").toLowerCase();
            if (os.startsWith("mac")) {
            	command = "open result.xlsx";
            } else {
            	command = "xdg-open result.xlsx";
            }
            try {
                Process process = runtime.exec(command);
                if (process.waitFor() != 0) {
                    if (process.exitValue() == 1) {
                        System.err.println("指令执行失败!");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if (ae.getActionCommand().equals("getCFG")) {
            Runtime run = Runtime.getRuntime();
            String command = "java soot.tools.CFGViewer -cp " + classPath + " -pp " +  mainClass + " -d sootOutput/" + mainClass;
            try {  
                Process process = run.exec(command);
                BufferedInputStream in = new BufferedInputStream(process.getInputStream());
                BufferedReader inBr = new BufferedReader(new InputStreamReader(in));
                String lineStr;
                while ((lineStr = inBr.readLine()) != null) {
                    System.out.println(lineStr);
                }
                if (process.waitFor() != 0) {  
                    if (process.exitValue() == 1) {
                        System.err.println("命令执行失败!");
                    } 
                }  
                inBr.close();  
                in.close();
            } catch (Exception e) {  
                e.printStackTrace();  
            }

            command = "find" + " ./sootOutput/" + mainClass + " -type f -name \"* *\" -print "
                    + "| "
                    + "while read name; do\n"
                    + "na=$(echo $name | tr ' ' '_')\n"
                    + "mv \"$name\" \"$na\"\n"
                    + "done\n";
            try { 
                List<String> commands = new ArrayList<String>();
                commands.add("sh");
                commands.add("-c");
                commands.add(command);
                ProcessBuilder processBuilder =new ProcessBuilder(commands);
                Process process = processBuilder.start();
                BufferedInputStream in = new BufferedInputStream(process.getInputStream());  
                BufferedReader inBr = new BufferedReader(new InputStreamReader(in));  
                String lineStr; 
                while ((lineStr = inBr.readLine()) != null) {
                    System.out.println(lineStr);
                }
                if (process.waitFor() != 0) {  
                    if (process.exitValue() == 1) {
                        System.err.println("命令执行失败!");
                    }
                }
                inBr.close();  
                in.close();  
            } catch (Exception e) {  
                e.printStackTrace();  
            }
            command = "find" + " ./sootOutput/" + mainClass + " -type f -name \"*.dot\" -print "
                    + "| "
                    + "while read name; do\n"
                    + "na=$(echo $name\".png\")\n"
                    + "dot -Tpng -o $na $name\n"
                    + "eog $na\n"
                    + "done\n";
            try { 
                List<String> commands = new ArrayList<String>();
                commands.add("sh");
                commands.add("-c");
                commands.add(command);
                ProcessBuilder processBuilder =new ProcessBuilder(commands);
                Process process = processBuilder.start();
                BufferedInputStream in = new BufferedInputStream(process.getInputStream());  
                BufferedReader inBr = new BufferedReader(new InputStreamReader(in));  
                String lineStr; 
                while ((lineStr = inBr.readLine()) != null) {
                    System.out.println(lineStr); 
                }
                if (process.waitFor() != 0) {  
                    if (process.exitValue() == 1) {
                        System.err.println("命令执行失败!");
                    }
                }
                result.setText("get CFG successfully!");
                inBr.close();  
                in.close();
            } catch (Exception e) {  
                e.printStackTrace();  
            }
            
            /*command = "open sootOutput/" + mainClass + "/*.png";
            try {
            	Runtime runtime = Runtime.getRuntime();
                Process process = runtime.exec(command);
                if (process.waitFor() != 0) {
                    if (process.exitValue() == 1) {
                        System.err.println(command);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }*/
        }
        else if (ae.getActionCommand().equals("goBack")) {
            f1.setVisible(true);
            f2.setVisible(false);
        }
    }
    
    public void showCodeText() {
        BufferedReader bReader = null;
        try {
            bReader = new BufferedReader(new FileReader(new File(classPath+"/"+mainClass+".java")));
            StringBuffer sBuffer = new StringBuffer();
            String readCode = null;
            while ((readCode = bReader.readLine()) != null) {
                sBuffer.append(readCode + "\n");
            }
            codeText.setText(sBuffer.toString());
        } catch (Exception e) {
            //若当前路径没有文件则提示用户
            codeText.setText("请确认文件路径:" + classPath + "目录下有文件!");
        } finally {
            if (bReader != null) {
                try {
                    bReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

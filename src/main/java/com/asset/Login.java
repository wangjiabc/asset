package com.asset;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;
import java.util.Random;
 
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.eclipse.jetty.util.security.Credential.MD5;

import com.asset.database.Connect;
import com.asset.tool.Md5;
import com.asset.tool.TestDB;
import com.rmi.server.Assets;
import com.voucher.manage.daoModel.Assets.Hidden_User;
 
public class Login extends JFrame implements ActionListener {
 String userName;
 String password;
 String captcha;
 public static String randomcaptcha;
 
  JLabel logoLabel, userNameLabel, passwordLabel, captchaLabel;
  JTextField userNameInput;
 // JTextField captchaInput;
  JPasswordField passwordInput;
  JButton login, logout;
 // jButton change;
  Panel panel;
  Connection connection;
 
  PreparedStatement pstmt = null;
  ResultSet rs = null;
  ResultSetMetaData rsmd;
  
 public Login() {
  setTitle("安全隐患监管系统登录");
  setSize(500, 300);
  setLocationRelativeTo(null);
  init();
  /*
  try {
	connection=TestDB.getConnection();
   } catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
   } catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
    }
    */
  setVisible(true);
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  setResizable(false);
 }
 
 public void init() {
  setLayout(null);
  logoLabel= new JLabel();
  URL url = getClass().getResource("welcome.gif");
  logoLabel.setIcon(new ImageIcon(url));
  logoLabel.setBounds(80, 10, 300, 70);
 // add(logoLabel);
 
  Font font=new java.awt.Font("Dialog",1,16);
  
  userNameLabel = new JLabel("帐号:");
  userNameLabel.setBounds(130, 60, 60, 40); 
  userNameLabel.setFont(font);
  add(userNameLabel);
  userNameInput = new JTextField();
  userNameInput.setBounds(180, 70, 150, 20);
  userNameInput.setSize(200,30);
  userNameInput.setFont(font);
  add(userNameInput);
 
  passwordLabel = new JLabel("密码:");
  passwordLabel.setBounds(130, 120, 60, 40);
  passwordLabel.setFont(font);
  add(passwordLabel);
  passwordInput = new JPasswordField();
  passwordInput.setBounds(180, 130, 150, 20);
  passwordInput.setSize(200,30);
  passwordInput.setFont(font);
  add(passwordInput);
 
  /*
  captchaLabel = new JLabel("验证码:");
  captchaLabel.setBounds(120, 180, 60, 40);
  captchaLabel.setFont(font);
  add(captchaLabel);
  captchaInput = new JTextField();
  captchaInput.setBounds(180, 190, 70, 20);
  captchaInput.setSize(80,30);
  captchaInput.setFont(font);
  add(captchaInput);

  panel = new PanelDemo();
  panel.setBounds(270, 196, 80, 20);
  panel.setSize(80,30);
  add(panel);
   
   
  change = new JButton("更换");
  change.setBounds(330, 196, 80, 20);
  change.setContentAreaFilled(false);
  change.setBorderPainted(false);
  add(change);
 */
  
  login = new JButton("确定");
  login.setBounds(120, 200, 120, 30);
  login.setFont(new java.awt.Font("",1,14));
 // login.setMnemonic(KeyEvent.VK_L);
  add(login);
  logout = new JButton("退出");
  logout.setBounds(270, 200, 120, 30);
 // logout.setMnemonic(KeyEvent.VK_X);
  logout.setFont(new java.awt.Font("",1,14));
  add(logout);
 
  userNameInput.addActionListener(this);
  passwordInput.addActionListener(this);
//  captchaInput.addActionListener(this);
 
  login.addActionListener(this);
  logout.addActionListener(this);
 // change.addActionListener(this);
 }
 
 public void actionPerformed(ActionEvent e) {
	 Assets assets = null;
	 try{
	  assets= new Connect().get();
	 }catch (java.lang.ExceptionInInitializerError ex) {
		// TODO: handle exception
		 JOptionPane.showMessageDialog(this, new JLabel("<html><h2>网络连接异常</html></h2>"));
		 dispose();
	}
  userName = userNameInput.getText();
  password = new String(passwordInput.getPassword());
 // captcha = captchaInput.getText();
  
  /*
  if (e.getSource() == change) {
   panel.repaint();
  }
  */
 
  if (e.getSource() == login) {	 
	  
	  if(userName.equals("")){
		  JOptionPane.showMessageDialog(this, new JLabel("<html><h2>帐号不能空</html></h2>"));
		  return;
	  }
	  
	  if(password.equals("")){
		  JOptionPane.showMessageDialog(this, new JLabel("<html><h2>密码不能空</html></h2>"));
		  return;
	  }
	  /*
	  if(captcha.equals("")){
		  JOptionPane.showMessageDialog(this, new JLabel("<html><h2>请输入验证码</html></h2>"));
		  return;
	  }
	  */
	  Map map=assets.selectHiddenUser(userName);
	  int state=(int) map.get("state");
	  Hidden_User hidden_User=(Hidden_User) map.get("row");	 
	//  if (captcha.equals(randomcaptcha.toLowerCase())) {
		  
		  if(state==0){
			  JOptionPane.showMessageDialog(this, new JLabel("<html><h2>用户不存在</html></h2>"));
			  return;
		  }
		  
		  String campusAdmin=hidden_User.getCampusAdmin();
		  String pw=hidden_User.getPassword();    
		  
		  if ((userName.equals(campusAdmin)) && (Md5.GetMD5Code(password).equals(pw))) {
			  System.out.println(randomcaptcha);
			  System.out.println(captcha);
    
			  Singleton.getInstance().setHidden_User(hidden_User);
	 
			  Hidden_User hidden_User2=new Hidden_User();
			  Date date=new Date();
			  hidden_User2.setEnter_time(date);
			  String[] where={"[Assets].[dbo].[Hidden_User].campusAdmin = ",campusAdmin};
			  hidden_User2.setWhere(where);
			  assets.updateHiddenUser(hidden_User2);
			  dispose();
			  
			  
			  if(Md5.GetMD5Code(password).equals(Md5.GetMD5Code("xl123"))){
				  JOptionPane.showMessageDialog(this, new JLabel("<html><h2>登录后请修改默认密码</html></h2>"));  
			  }
			  
			  new MainApp().launch(Main.class, "");  
			  
			  
		  	} else if (userName.equals(campusAdmin)){
			  JOptionPane.showMessageDialog(this, new JLabel("<html><h2>密码错误!</html></h2>"));
		  	} else {
		  	   JOptionPane.showMessageDialog(this, new JLabel("<html><h2>用户不存在!</html></h2>"));
			}
		  
		/*  } else {    
			  JOptionPane.showMessageDialog(this, new JLabel("<html><h2>验证码错误</html></h2>"));
			  panel.repaint();
		  }  */ 
   }
  
  if (e.getSource() == logout) {
   JOptionPane.showMessageDialog(this, new JLabel("<html><h2>退出系统</html></h2>"));
   System.exit(0);
   //dispose();
  }
 }
 public static void main(String[] args) {
   new Login();
 }
}
class PanelDemo extends Panel {
 
 public void paint(Graphics g) {
  int width = 80;
  int height = 20;
  g.setColor(Color.LIGHT_GRAY);
  g.fillRect(0, 0, width, height);
  g.setColor(Color.BLACK);
  g.drawRect(0, 0, width, height);
  Random rd = new Random();
  for (int i = 0; i < 100; i++) {
   int x = rd.nextInt(width) - 2;
   int y = rd.nextInt(height) - 2;
   g.setColor(Color.RED);
   g.drawOval(x, y, 2, 2);
  }
  g.setFont(new Font("", Font.BOLD, 20));
  g.setColor(Color.BLUE);
  char[] c = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
  StringBuffer sb = new StringBuffer();
  for (int i = 0; i < 4; i++) {
   int index = rd.nextInt(c.length);
   sb.append(c[index] + " ");
  }
  g.drawString(sb.toString(), 0, 18);
 
  String str = sb.toString().replaceAll(" ", "");
  Login.randomcaptcha = str;
 }
}
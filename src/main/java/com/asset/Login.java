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
import java.util.Random;
 
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.asset.tool.Md5;
import com.asset.tool.TestDB;
 
public class Login extends JFrame implements ActionListener {
 String userName;
 String password;
 String userName2;
 String password2;
 String captcha;
 public static String randomcaptcha;
 
  JLabel logoLabel, userNameLabel, passwordLabel, captchaLabel;
  JTextField userNameInput, captchaInput;
  JPasswordField passwordInput;
  JButton login, logout,change;
  Panel panel;
  Connection connection;
 
  PreparedStatement pstmt = null;
  ResultSet rs = null;
  ResultSetMetaData rsmd;
  
 public Login() {
  setTitle("aaa");
  setSize(400, 300);
  setLocationRelativeTo(null);
  init();
  try {
	connection=TestDB.getConnection();
   } catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
   } catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
    }
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
  add(logoLabel);
 
  userNameLabel = new JLabel("name:");
  userNameLabel.setBounds(90, 90, 60, 40);
  add(userNameLabel);
  userNameInput = new JTextField();
  userNameInput.setBounds(150, 100, 150, 20);
  add(userNameInput);
 
  passwordLabel = new JLabel("passwod:");
  passwordLabel.setBounds(90, 120, 60, 40);
  add(passwordLabel);
  passwordInput = new JPasswordField();
  passwordInput.setBounds(150, 130, 150, 20);
  add(passwordInput);
 
  captchaLabel = new JLabel("code:");
  captchaLabel.setBounds(90, 150, 60, 40);
  add(captchaLabel);
  captchaInput = new JTextField();
  captchaInput.setBounds(150, 160, 70, 20);
  add(captchaInput);
 
  panel = new PanelDemo();
  panel.setBounds(220, 160, 80, 20);
  add(panel);
   
   
  change = new JButton("go");
  change.setBounds(300, 160, 80, 20);
  change.setContentAreaFilled(false);
  change.setBorderPainted(false);
  add(change);
 
  login = new JButton("login");
  login.setBounds(70, 200, 120, 30);
  login.setMnemonic(KeyEvent.VK_L);
  add(login);
  logout = new JButton("exit");
  logout.setBounds(210, 200, 120, 30);
  logout.setMnemonic(KeyEvent.VK_X);
  add(logout);
 
  userNameInput.addActionListener(this);
  passwordInput.addActionListener(this);
  captchaInput.addActionListener(this);
 
  login.addActionListener(this);
  logout.addActionListener(this);
  change.addActionListener(this);
 }
 
 public void actionPerformed(ActionEvent e) {
 
  userName = userNameInput.getText();
  password = new String(passwordInput.getPassword());
  captcha = captchaInput.getText();
   
  if (e.getSource() == change) {
   panel.repaint();
  }
  
  if (e.getSource() == login) {	
	  try {
		  pstmt = connection.prepareStatement("select campus_admin,password from campus_admin where campus_admin = ?");
		  pstmt.setString(1, userName);
          rs = pstmt.executeQuery();
          while (rs.next()) { // 调试代码
        	  userName2=rs.getString(1);
        	  password2=rs.getString(2);
           }
	     } catch (SQLException e1) {
		  // TODO Auto-generated catch block
		   e1.printStackTrace();
		}
 
   if ((userName.equals(userName2)) && (password2.equals(Md5.GetMD5Code(password)))) {
	   System.out.println(randomcaptcha);
	   System.out.println(captcha);
    if (captcha.equals(randomcaptcha.toLowerCase())) {
       JOptionPane.showMessageDialog(this, "登录成功");
       dispose();
       new MainApp().launch(MainApp.class, "");     
    } else {
       JOptionPane.showMessageDialog(this, "验证码错误");
       panel.repaint();
    }
   } else {
    JOptionPane.showMessageDialog(this, "对不起，你没有登陆权限!");
   }
  }
  if (e.getSource() == logout) {
   JOptionPane.showMessageDialog(this, "»¶Ó­ÏÂ´ÎÔÙÀ´£¡");
   //System.exit(0);
   dispose();
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
  g.setFont(new Font("ºÚÌå", Font.BOLD, 20));
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
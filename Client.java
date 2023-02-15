import javax.swing.*;
import javax.swing.border.*;      // Package for border of msg
import java.awt.*;
import java.awt.event.*;
import java.util.*;               // Used for Calendar class
import java.text.*;            // Used for date format
import java.net.*;           // Used for Socket programing 
import java.io.*;            // Used for reading writting input output


public class Client  implements ActionListener {  // Action Listener interface comes from package java.awt.event.*;
   
  JTextField text;  // Made globally so that can be used by other functions
  static JPanel a1;
  static Box vertical=Box.createVerticalBox();    // messeges vertically ek ke niche ek aae is liye vertical box use kiya
  static JFrame f= new JFrame();
  static DataOutputStream dout;

  Client(){    // Constructor under which code will be written
    
    f.setLayout(null);
    JPanel p1= new JPanel();  // p1 object is panel 1 in the class JPanel
    p1.setBackground(new Color(7,94,84));
    p1.setBounds(0,0,450,60);
    p1.setLayout(null);
    f.add(p1);     // add function is used to add components int the frame
    
    // Functions performed to add back image
    ImageIcon i1= new ImageIcon(ClassLoader.getSystemResource("icons/3.png"));
    Image i2= i1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT); // image size scaled for better visibility
    ImageIcon i3= new ImageIcon(i2);    // new image icon i3 made b'coz scaled image i2 cannot be directly put into JLabel back
    JLabel back= new JLabel(i3);
    back.setBounds(5,20,25,25);
    p1.add(back);

    // Functions used for the back button working
     back.addMouseListener(new MouseAdapter(){
        public void mouseClicked(MouseEvent ae){
        System.exit(0);
        } 
     });


     // Function to add profile pic
    ImageIcon i4= new ImageIcon(ClassLoader.getSystemResource("icons/2.png"));
    Image i5= i4.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT); // image size scaled for better visibility
    ImageIcon i6= new ImageIcon(i5);    // new image icon i3 made b'coz scaled image i2 cannot be directly put into JLabel back
    JLabel profile= new JLabel(i6);
    profile.setBounds(40,6,50,50);
    p1.add(profile);
    
    
    // Fuction to add Videocall pic
    ImageIcon i10= new ImageIcon(ClassLoader.getSystemResource("icons/video.png"));
    Image i11= i10.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT); // image size scaled for better visibility
    ImageIcon i12= new ImageIcon(i11);    // new image icon i3 made b'coz scaled image i2 cannot be directly put into JLabel back
    JLabel videocall= new JLabel(i12);
    videocall.setBounds(300,23,25,15);
    p1.add(videocall);
    


  // Fuction to add call pic
    ImageIcon i7= new ImageIcon(ClassLoader.getSystemResource("icons/phone.png"));
    Image i8= i7.getImage().getScaledInstance(30, 25, Image.SCALE_DEFAULT); // image size scaled for better visibility
    ImageIcon i9= new ImageIcon(i8);    // new image icon i3 made b'coz scaled image i2 cannot be directly put into JLabel back
    JLabel call= new JLabel(i9);
    call.setBounds(355,23,30,20);
    p1.add(call);



  // Function to add setting pic   
    ImageIcon i13= new ImageIcon(ClassLoader.getSystemResource("icons/3icon.png"));
    Image i14= i13.getImage().getScaledInstance(10, 20, Image.SCALE_DEFAULT); // image size scaled for better visibility
    ImageIcon i15= new ImageIcon(i14);    // new image icon i3 made b'coz scaled image i2 cannot be directly put into JLabel back
    JLabel setting= new JLabel(i15);
    setting.setBounds(410,20,10,20);
    p1.add(setting);
    


  // Function to add Name
    JLabel name=new JLabel("Bunty"); // here name is the object used in JLabel
    name.setBounds(110,6,120,25);  
    name.setForeground(Color.WHITE);
    name.setFont(new Font("SAN_SERIF",Font.BOLD, 18));
    p1.add(name);


  // Function to add status  
    JLabel status=new JLabel("Active Now"); // here name is the object used in JLabel
    status.setBounds(110,35,120,25);  
    status.setForeground(Color.WHITE);
    status.setFont(new Font("SAN_SERIF",Font.BOLD, 14));
    p1.add(status);


  // Function for second panel a1
    a1=new JPanel();
    a1.setBounds(5,66,440,570);
    f.add(a1);
  


  // Function for text enter by user
    text=new JTextField();
    text.setBounds(5,646,310,40);
    text.setFont(new Font("SAN_SERIF",Font.PLAIN,16));
    f.add(text);

 
  // Function for "SEND BUTTON"
  JButton send=new JButton("Send");
  send.setBounds(320,646,123,40);
  send.setBackground(new Color(7,94,84));
  send.setFont(new Font("SAN_SERIF",Font.PLAIN,16));
  send.setForeground(Color.WHITE);

  send.addActionListener(this);  // Used for action of send button, its action performed code is written in below actionPerformed function
  f.add(send);  



  // Main frame parameters
  f.setSize(450,700);  // Frame size
  f.setLocation(700,100);       // Frame location

  f.setUndecorated(true);   // to remove default closing layout

  f.getContentPane().setBackground(Color.WHITE);  // frame background color is white
  f.setVisible(true);              // Frame visibility true
  }

  // Function action performed
  public void actionPerformed(ActionEvent ae){    // abstract method hai interface ActionListener me isliye isko override karaya
    
    try{
    String out=text.getText();                // getText function used for taking out text from any object
    
            // As we cannot directly put string in the panel so send string inside JLabel
        
    JPanel p2=  formatLabel(out);         // new panel p2 which calls formatLabel funtion to customize msg panel
    

    a1.setLayout(new BorderLayout());  // Border layout is used to use panel from right


    JPanel right=new JPanel(new BorderLayout());   // In this panel message will display
    right.add(p2, BorderLayout.LINE_END);          // panel will be at the right end due to the function line end     
    vertical.add(right);                         // vertical box is used so that msg will come vertically downward
    vertical.add(Box.createVerticalStrut(15));  // spacing btwn next vertical boxes is 15

    a1.add(vertical,BorderLayout.PAGE_START);  // Here vertical is added in panel a1 and start from page start 
   
    dout.writeUTF(out);  // To send msg to Server

    text.setText("");
    // below three functions are used to reload the frame as panel is customized again
    f.repaint();
    f.invalidate();
    f.validate();
    
  }catch(Exception e){
      e.printStackTrace();
    }
  }

  // Function to customize the msg shown like inside the box
  public static JPanel formatLabel (String out){
       JPanel panel=new JPanel();
       panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));  // panel will be along yaxis one below the other
       
       JLabel output =new JLabel(out);
       output.setFont (new Font("Tahoma",Font.PLAIN,16));
       output.setBackground(new Color(37,211,102));
       output.setOpaque(true);   // For the visibility of the background color
       
       //Below code is used so that msg display on a little bit left side
       output.setBorder(new EmptyBorder(15, 15, 15, 50));  // setborder comes under Border package which is child package of Swing package
                                                                                  
       panel.add(output);   // at last add output label in the panel


       //Below code will show real time of msg send
       Calendar cal= Calendar.getInstance();    // From this class we choose date
       SimpleDateFormat sdf=new SimpleDateFormat("HH:mm");   // From this class we choose format of time

       JLabel time=new JLabel();    // Text form me show karne ke liye JLabel ka use
       time.setText(sdf.format(cal.getTime()));  // setText function enables us to put real time from Cal class and  SDF format class
       panel.add(time);
       return panel;
  }


    public static void main(String args[]){
       new Client(); // anonymus object without name

       try{
            Socket s=new Socket("127.0.0.1",6001);     // Socket for client with host and Server address
            DataInputStream din=new DataInputStream(s.getInputStream());      // To receive msg
            dout=new DataOutputStream(s.getOutputStream());  // To send msg

            while(true){
              a1.setLayout(new BorderLayout());
              String msg=din.readUTF();  // To read upcoming msgs
              JPanel panel= formatLabel(msg);   

              JPanel left=new JPanel(new BorderLayout());
              left.add(panel,BorderLayout.LINE_START);
              vertical.add(left);
      
              vertical.add(Box.createVerticalStrut(15));
              a1.add(vertical,BorderLayout.PAGE_START);
              f.validate();

             }
       }catch(Exception e){
          e.printStackTrace();
       }


    }

}
package team3.passpasspass.VM.controller;

import team3.passpasspass.VM.controller.model.MaintainerFileManager;
import team3.passpasspass.VM.controller.model.NumberObserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaintainerPanel extends JFrame{
	
	boolean isLogin = false;
	String coinsSelected = null;
	String coinsAvailable = null;
	String drinkLine = null;
	String drinkSelected = null;
	String drinkMoney = null;
	String drinkAvailable = null;
	int totalCash = 0;

	public MaintainerPanel() {

	}
	
	public void setIsLogin() {
		isLogin = true;
	}
	
	public void setLogout() {
		isLogin = false;
	}
	
	public void createMaintainerJFrame(String maintainerFrametitle) {
		JFrame maintainerJF = new JFrame(maintainerFrametitle);
		Container maintainerContainer = maintainerJF.getContentPane();
		

		JLabel jlMaintainerTitle = new JLabel("VMCS - Maintainer Penel");
		JLabel jlMaintainerPassword = new JLabel("Password:");
		JPasswordField jpfMaintainerPassword = new JPasswordField();
		JButton jbMaintainerPasswordValid = new JButton("Valid Password");
		JButton jbMaintainerPasswordInvalid = new JButton("Invalid Password");
		
		jlMaintainerTitle.setBounds(250,0,400,50);
		jlMaintainerTitle.setFont(new Font("",1,25));
		jlMaintainerPassword.setBounds(300,100,100,20);
		jlMaintainerPassword.setFont(new Font("",1,15));
		jpfMaintainerPassword.setBounds(400,100,80,20);
		
		jbMaintainerPasswordValid.setBounds(100,150,250,50);
		jbMaintainerPasswordValid.setFont(new Font("",1,15));
		jbMaintainerPasswordValid.setForeground(Color.white);
		jbMaintainerPasswordValid.setBackground(Color.gray);
		
		jbMaintainerPasswordValid.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
								
			}
		});
		
		jbMaintainerPasswordInvalid.setBounds(360,150,280,50);
		jbMaintainerPasswordInvalid.setFont(new Font("",1,15));
		jbMaintainerPasswordInvalid.setForeground(Color.black);
		jbMaintainerPasswordInvalid.setBackground(Color.gray);
		
		maintainerContainer.add(jlMaintainerTitle);
		maintainerContainer.add(jlMaintainerPassword);
		maintainerContainer.add(jpfMaintainerPassword);
		maintainerContainer.add(jbMaintainerPasswordValid);
		maintainerContainer.add(jbMaintainerPasswordInvalid);
		
		jpfMaintainerPassword.addKeyListener(new KeyAdapter(){  
            public void keyTyped(KeyEvent passwordTypeEvent) {  
            	char passwordChar = passwordTypeEvent.getKeyChar();
            	char[] passwordEntered = jpfMaintainerPassword.getPassword();
            	String maintainerPassword = "123a";
            	char[] maintainerPasswordString  = maintainerPassword.toCharArray();
            	if(passwordChar == KeyEvent.VK_ENTER) {
            		if(Arrays.equals(passwordEntered, maintainerPasswordString)) {
            			setIsLogin();
            			jbMaintainerPasswordValid.setBackground(Color.green);
            			jbMaintainerPasswordInvalid.setForeground(Color.black);
            			jbMaintainerPasswordInvalid.setBackground(Color.gray);
            		}
            		else{
            			setLogout();
            			jbMaintainerPasswordValid.setBackground(Color.gray);
            			jbMaintainerPasswordInvalid.setForeground(Color.gray);
            			jbMaintainerPasswordInvalid.setBackground(Color.red);
            		}
            	};
            	
            }  
        }); 
		
		//�ڶ�����
		JLabel jlCoins = new JLabel("Quantity of Coins Available");	
		jlCoins.setBounds(5,250,300,20);
		jlCoins.setFont(new Font("",1,15));
		
		int coinsLine = new MaintainerFileManager().getLines("./Data/dwd_drink_info.csv");
		JPanel jpCoins = new JPanel();
		jpCoins.setLayout(new GridLayout(coinsLine,1,10,0));
		jpCoins.setBounds(5,270,350,140);
		
		JTextField jtfTotolCoins = new JTextField(coinsAvailable);
		jtfTotolCoins.setBounds(380,280,50,20);
		jtfTotolCoins.setHorizontalAlignment(JTextField.CENTER);
		jtfTotolCoins.setForeground(Color.yellow);
		jtfTotolCoins.setBackground(Color.black);
		
		try {
			BufferedReader coinsReader = new BufferedReader(new FileReader("./Data/dwd_money_stat.csv"));
			String line = null;
			int lineIndex = 0;
			while((line=coinsReader.readLine())!=null) {
				if(lineIndex > 0) {
					String item[] = line.split(",");
					totalCash = totalCash + Integer.parseInt(item[0])*Integer.parseInt(item[1]);
					String coinsName = null;
					if(item[0].equals("100")) {
						coinsName = "$" + item[0];
					}else {
						coinsName = item[0] + "c";
					}
					JButton jbCoin = new JButton(coinsName);
					
					jbCoin.setBackground(Color.white);
					jpCoins.add(jbCoin);
								
					jbCoin.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent arg0) {
							try {
								BufferedReader coinsReader2 = new BufferedReader(new FileReader("./Data/dwd_money_stat.csv"));
								String line2 = null;
								int line2Index = 0;
								while((line2=coinsReader2.readLine())!=null) {
									if(line2Index > 0) {
										String item2[] = line2.split(",");
										String coinsName2 = null;
										if(item2[0].equals("100")) {
											coinsName2 = "$" + item2[0];
										} else {
											coinsName2 = item2[0] + "c";
										}
										
										if(coinsName2.equals(((JButton)arg0.getSource()).getText())) {
											coinsAvailable = item2[1];
											coinsSelected = ((JButton)arg0.getSource()).getText();	
											jtfTotolCoins.setText(coinsAvailable);;
										}
									}
									line2Index++;
								}
								coinsReader2.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					});
				}
				lineIndex++;
			}
			coinsReader.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		maintainerContainer.add(jlCoins);
		maintainerContainer.add(jpCoins);
		maintainerContainer.add(jtfTotolCoins);
		
		

		JLabel jlDrinksAvailable = new JLabel("Quantity of Drinks Available");	
		jlDrinksAvailable.setBounds(5,450,300,20);
		jlDrinksAvailable.setFont(new Font("",1,15));
		
		JPanel jpDrinks = new JPanel();
		int drinksTotalLine = new MaintainerFileManager().getLines("./Data/dwd_drink_info.csv");
		jpDrinks.setLayout(new GridLayout(drinksTotalLine,1,10,0));
		jpDrinks.setBounds(5,470,350,140);
		
		JTextField jtfDrinksAvailable = new JTextField(drinkMoney);
		jtfDrinksAvailable.setBounds(380,480,50,20);
		jtfDrinksAvailable.setHorizontalAlignment(JTextField.CENTER);
		jtfDrinksAvailable.setForeground(Color.yellow);
		jtfDrinksAvailable.setBackground(Color.black);
		
		JLabel jlBrandPrice = new JLabel("Brand Price");
		jlBrandPrice.setBounds(300,620,100,20);
		jlBrandPrice.setFont(new Font("",1,15));
		JTextField jtfBrandPrice = new JTextField(drinkMoney);
		jtfBrandPrice.setBounds(400,620,80,20);
		
		jtfBrandPrice.addKeyListener(new KeyAdapter(){  
            public void keyTyped(KeyEvent brandModifyEvent) {  
            	char brandNewPrice = brandModifyEvent.getKeyChar();
            	String brandNewPriceEntered = jtfBrandPrice.getText(); 
            	if(brandNewPrice == KeyEvent.VK_ENTER) {
            		System.out.println(brandNewPriceEntered);
            		List<String[]> list = new ArrayList<>();
        			String line = null;
            		try {
            			BufferedReader reader = new BufferedReader(new FileReader("./Data/dwd_drink_info.csv"));
            			
               			
            			while ((line=reader.readLine())!=null) {
            				list.add(line.split(","));
						}
            			reader.close();
            			
            			for(int i = 0; i < list.size() ; i++) {
            				if(drinkLine != null && i == Integer.parseInt(drinkLine)) {
            					list.get(i)[3] = brandNewPriceEntered;
            					System.out.println(brandNewPriceEntered);
            				}
            			};
            			
            			BufferedWriter writer = new BufferedWriter(new FileWriter("./Data/dwd_drink_info.csv"));
            			
            			for (int i = 0; i < list.size(); i++) {
            				for (int j = 0; j < list.get(i).length; j++) {
            					writer.write(list.get(i)[j].toString() + ",");
            					if (j == list.get(i).length - 1) {
            						writer.newLine();
            					}
            				}
            			}
            			writer.close();
            		            			          			
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
            	};
            }  
        }); 
		

		
		try {
			BufferedReader drinksReader = new BufferedReader(new FileReader("./Data/dwd_drink_info.csv"));
			String line = null;
			int lineIndex = 0;
			
			while((line=drinksReader.readLine())!=null) {
				String item1[] = line.split(",");//���ݶ���
				if(lineIndex > 0) {
					JButton jbDrink = new JButton(item1[1]);		
					jbDrink.setBackground(Color.white);
					jpDrinks.add(jbDrink);
				
					jbDrink.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent arg0) {
							try {
								BufferedReader drinksReader2 = new BufferedReader(new FileReader("./Data/dwd_drink_info.csv"));
								String line2 = null;
								while((line2=drinksReader2.readLine())!=null) {
									String item2[] = line2.split(",");
										if(item2[1].equals(((JButton)arg0.getSource()).getText())) {
										//jbDrink.setBorder(BorderFactory.createLineBorder(Color.red));
										drinkLine = item2[0];
										drinkAvailable = item2[2];
										drinkMoney = item2[3];
										drinkSelected = ((JButton)arg0.getSource()).getText();
										jtfDrinksAvailable.setText(drinkAvailable);
										jtfBrandPrice.setText(drinkMoney);
									}
									System.out.println(drinkLine);
									System.out.println(drinkSelected);
									System.out.println(drinkAvailable);
									System.out.println(drinkMoney);
								}
								drinksReader2.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					});	
				}
				lineIndex++;
			}
			drinksReader.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
			
		maintainerContainer.add(jlDrinksAvailable);
		maintainerContainer.add(jpDrinks);
		maintainerContainer.add(jtfDrinksAvailable);
		maintainerContainer.add(jlBrandPrice);
		maintainerContainer.add(jtfBrandPrice);
	
		

		JButton jbShowTotal = new JButton("Show Total Cash Held");
		JTextField jtfTotalCash = new JTextField();
				
		jbShowTotal.setBounds(200,700,300,30);
		jbShowTotal.setFont(new Font("",1,15));
		jbShowTotal.setBackground(Color.white);
		jtfTotalCash.setBounds(510,705,80,20);
		jtfTotalCash.setHorizontalAlignment(JTextField.CENTER);
		jtfTotalCash.setForeground(Color.yellow);
		jtfTotalCash.setBackground(Color.black);
		
		jbShowTotal.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				jtfTotalCash.setText(String.valueOf((totalCash) + "c"));
			}
		});
		
		JButton jbCollectCash = new JButton("Press to Collect All Cash");
		JLabel jlCollectCash = new JLabel("Collect Cash:");
		JTextField jtfCollectCash = new JTextField("0 c");
		
		jbCollectCash.setBounds(250,750,300,30);
		jbCollectCash.setFont(new Font("",1,15));
		jbCollectCash.setBackground(Color.white);
		jlCollectCash.setBounds(320,800,100,20);
		jlCollectCash.setFont(new Font("",1,15));
		jtfCollectCash.setBounds(430,800,50,20);
		jtfCollectCash.setHorizontalAlignment(JTextField.CENTER);
		jtfCollectCash.setForeground(Color.yellow);
		jtfCollectCash.setBackground(Color.black);	
		
		jbCollectCash.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				jtfCollectCash.setText(String.valueOf((totalCash) + "c"));
				totalCash = 0;
				
				new NumberObserver().maintainerCollectCoins("./Data/dwd_money_stat.csv");
			}
		});

		JButton jbFinished = new JButton("Press Here when Finished");
		jbFinished.setBounds(240,880,300,30);
		jbFinished.setFont(new Font("",1,15));
		jbFinished.setBackground(Color.white);
		
		jbFinished.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setLogout();
				jlCoins.setVisible(isLogin);
        		jpCoins.setVisible(isLogin);
        		jtfTotolCoins.setVisible(isLogin);
        		jlDrinksAvailable.setVisible(isLogin);
        		jpDrinks.setVisible(isLogin);
        		jtfDrinksAvailable.setVisible(isLogin);
        		jlBrandPrice.setVisible(isLogin);
        		jtfBrandPrice.setVisible(isLogin);
        		jbShowTotal.setVisible(isLogin);
        		jtfTotalCash.setVisible(isLogin);
        		jbCollectCash.setVisible(isLogin);
        		jlCollectCash.setVisible(isLogin);
        		jtfCollectCash.setVisible(isLogin);
        		jbFinished.setVisible(isLogin);
        		jbMaintainerPasswordValid.setBackground(Color.gray);
    			jbMaintainerPasswordInvalid.setBackground(Color.gray);
    			jpfMaintainerPassword.setText("");
			}
		});
		
		maintainerContainer.add(jbShowTotal);
		maintainerContainer.add(jtfTotalCash);
		maintainerContainer.add(jbCollectCash);
		maintainerContainer.add(jlCollectCash);
		maintainerContainer.add(jtfCollectCash);
		maintainerContainer.add(jbFinished);
		
		jlCoins.setVisible(isLogin);
		jpCoins.setVisible(isLogin);
		jtfTotolCoins.setVisible(isLogin);
		jlDrinksAvailable.setVisible(isLogin);
		jpDrinks.setVisible(isLogin);
		jtfDrinksAvailable.setVisible(isLogin);
		jlBrandPrice.setVisible(isLogin);
		jtfBrandPrice.setVisible(isLogin);
		jbShowTotal.setVisible(isLogin);
		jtfTotalCash.setVisible(isLogin);
		jbCollectCash.setVisible(isLogin);
		jlCollectCash.setVisible(isLogin);
		jtfCollectCash.setVisible(isLogin);
		jbFinished.setVisible(isLogin);
		
		jpfMaintainerPassword.addKeyListener(new KeyAdapter(){  
            public void keyTyped(KeyEvent passwordTypeEvent) {  
            	char passwordChar = passwordTypeEvent.getKeyChar();
            	char[] passwordEntered = jpfMaintainerPassword.getPassword();
            	String maintainerPassword = "123a";
            	char[] maintainerPasswordString  = maintainerPassword.toCharArray();
            	if(passwordChar == KeyEvent.VK_ENTER) {
            		if(Arrays.equals(passwordEntered, maintainerPasswordString)) {
            			setIsLogin();
            			jbMaintainerPasswordValid.setBackground(Color.green);
            			jbMaintainerPasswordInvalid.setForeground(Color.black);
            			jbMaintainerPasswordInvalid.setBackground(Color.gray);
            		}
            		else{
            			setLogout();
            			jbMaintainerPasswordValid.setBackground(Color.gray);
            			jbMaintainerPasswordInvalid.setForeground(Color.gray);
            			jbMaintainerPasswordInvalid.setBackground(Color.red);
            		}
            	};
            	jlCoins.setVisible(isLogin);
        		jpCoins.setVisible(isLogin);
        		jtfTotolCoins.setVisible(isLogin);
        		jlDrinksAvailable.setVisible(isLogin);
        		jpDrinks.setVisible(isLogin);
        		jtfDrinksAvailable.setVisible(isLogin);
        		jlBrandPrice.setVisible(isLogin);
        		jtfBrandPrice.setVisible(isLogin);
        		jbShowTotal.setVisible(isLogin);
        		jtfTotalCash.setVisible(isLogin);
        		jbCollectCash.setVisible(isLogin);
        		jlCollectCash.setVisible(isLogin);
        		jtfCollectCash.setVisible(isLogin);
        		jbFinished.setVisible(isLogin);
            }  
        }); 
		
		maintainerContainer.setLayout(null);
		
		maintainerJF.setVisible(true);
		maintainerJF.setSize(800,1000);
		
		maintainerJF.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
}



























//*peijun原版*//
//public class MaintainerPanel extends JFrame{
//
//	boolean isLogin = false;
//	String coinsSelected = null;
//	String coinsAvailable = null;
//	String drinkLine = null;
//	String drinkSelected = null;
//	String drinkMoney = null;
//	String drinkAvailable = null;
//	int totalCash = 0;
//
//	public MaintainerPanel() {
//
//	}
//
//	public void setIsLogin() {
//		isLogin = true;
//	}
//
//	public void setLogout() {
//		isLogin = false;
//	}
//
//	public void createMaintainerJFrame(String maintainerFrametitle) {
//		JFrame maintainerJF = new JFrame(maintainerFrametitle);
//		Container maintainerContainer = maintainerJF.getContentPane();
//
//
//		JLabel jlMaintainerTitle = new JLabel("VMCS - Maintainer Penel");
//		JLabel jlMaintainerPassword = new JLabel("Password:");
//		JPasswordField jpfMaintainerPassword = new JPasswordField();
//		JButton jbMaintainerPasswordValid = new JButton("Valid Password");
//		JButton jbMaintainerPasswordInvalid = new JButton("Invalid Password");
//
//		jlMaintainerTitle.setBounds(250,0,400,50);
//		jlMaintainerTitle.setFont(new Font("",1,25));
//		jlMaintainerPassword.setBounds(300,100,100,20);
//		jlMaintainerPassword.setFont(new Font("",1,15));
//		jpfMaintainerPassword.setBounds(400,100,80,20);
//
//		jbMaintainerPasswordValid.setBounds(100,150,250,50);
//		jbMaintainerPasswordValid.setFont(new Font("",1,15));
//		jbMaintainerPasswordValid.setForeground(Color.white);
//		jbMaintainerPasswordValid.setBackground(Color.gray);
//
//		jbMaintainerPasswordValid.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//
//			}
//		});
//
//		jbMaintainerPasswordInvalid.setBounds(360,150,280,50);
//		jbMaintainerPasswordInvalid.setFont(new Font("",1,15));
//		jbMaintainerPasswordInvalid.setForeground(Color.black);
//		jbMaintainerPasswordInvalid.setBackground(Color.gray);
//
//		maintainerContainer.add(jlMaintainerTitle);
//		maintainerContainer.add(jlMaintainerPassword);
//		maintainerContainer.add(jpfMaintainerPassword);
//		maintainerContainer.add(jbMaintainerPasswordValid);
//		maintainerContainer.add(jbMaintainerPasswordInvalid);
//
//		jpfMaintainerPassword.addKeyListener(new KeyAdapter(){
//			public void keyTyped(KeyEvent passwordTypeEvent) {
//				char passwordChar = passwordTypeEvent.getKeyChar();
//				char[] passwordEntered = jpfMaintainerPassword.getPassword();
//				String maintainerPassword = "123a";
//				char[] maintainerPasswordString  = maintainerPassword.toCharArray();
//				if(passwordChar == KeyEvent.VK_ENTER) {
//					if(Arrays.equals(passwordEntered, maintainerPasswordString)) {
//						setIsLogin();
//						jbMaintainerPasswordValid.setBackground(Color.green);
//						jbMaintainerPasswordInvalid.setForeground(Color.black);
//						jbMaintainerPasswordInvalid.setBackground(Color.gray);
//					}
//					else{
//						setLogout();
//						jbMaintainerPasswordValid.setBackground(Color.gray);
//						jbMaintainerPasswordInvalid.setForeground(Color.gray);
//						jbMaintainerPasswordInvalid.setBackground(Color.red);
//					}
//				};
//
//			}
//		});
//
//		//�ڶ�����
//		JLabel jlCoins = new JLabel("Quantity of Coins Available");
//		jlCoins.setBounds(5,250,300,20);
//		jlCoins.setFont(new Font("",1,15));
//
//		int coinsLine = new MaintainerFileManager().getLines("./Data/dwd_drink_info.csv");
//		JPanel jpCoins = new JPanel();
//		jpCoins.setLayout(new GridLayout(coinsLine,1,10,0));
//		jpCoins.setBounds(5,270,350,140);
//
//		JTextField jtfTotolCoins = new JTextField(coinsAvailable);
//		jtfTotolCoins.setBounds(380,280,50,20);
//		jtfTotolCoins.setHorizontalAlignment(JTextField.CENTER);
//		jtfTotolCoins.setForeground(Color.yellow);
//		jtfTotolCoins.setBackground(Color.black);
//
//		try {
//			BufferedReader coinsReader = new BufferedReader(new FileReader("./Data/dwd_money_stat.csv"));
//			String line = null;
//			int lineIndex = 0;
//			while((line=coinsReader.readLine())!=null) {
//				if(lineIndex > 0) {
//					String item[] = line.split(",");
//					totalCash = totalCash + Integer.parseInt(item[0])*Integer.parseInt(item[1]);
//					String coinsName = null;
//					if(item[0].equals("100")) {
//						coinsName = "$" + item[0];
//					}else {
//						coinsName = item[0] + "c";
//					}
//					JButton jbCoin = new JButton(coinsName);
//
//					jbCoin.setBackground(Color.white);
//					jpCoins.add(jbCoin);
//
//					jbCoin.addActionListener(new ActionListener() {
//						@Override
//						public void actionPerformed(ActionEvent arg0) {
//							try {
//								BufferedReader coinsReader2 = new BufferedReader(new FileReader("./Data/dwd_money_stat.csv"));
//								String line2 = null;
//								int line2Index = 0;
//								while((line2=coinsReader2.readLine())!=null) {
//									if(line2Index > 0) {
//										String item2[] = line2.split(",");
//										String coinsName2 = null;
//										if(item2[0].equals("100")) {
//											coinsName2 = "$" + item2[0];
//										} else {
//											coinsName2 = item2[0] + "c";
//										}
//
//										if(coinsName2.equals(((JButton)arg0.getSource()).getText())) {
//											coinsAvailable = item2[1];
//											coinsSelected = ((JButton)arg0.getSource()).getText();
//											jtfTotolCoins.setText(coinsAvailable);;
//										}
//									}
//									line2Index++;
//								}
//								coinsReader2.close();
//							} catch (IOException e) {
//								e.printStackTrace();
//							}
//						}
//					});
//				}
//				lineIndex++;
//			}
//			coinsReader.close();
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//
//		maintainerContainer.add(jlCoins);
//		maintainerContainer.add(jpCoins);
//		maintainerContainer.add(jtfTotolCoins);
//
//
//
//		JLabel jlDrinksAvailable = new JLabel("Quantity of Drinks Available");
//		jlDrinksAvailable.setBounds(5,450,300,20);
//		jlDrinksAvailable.setFont(new Font("",1,15));
//
//		JPanel jpDrinks = new JPanel();
//		int drinksTotalLine = new MaintainerFileManager().getLines("./Data/dwd_drink_info.csv");
//		jpDrinks.setLayout(new GridLayout(drinksTotalLine,1,10,0));
//		jpDrinks.setBounds(5,470,350,140);
//
//		JTextField jtfDrinksAvailable = new JTextField(drinkMoney);
//		jtfDrinksAvailable.setBounds(380,480,50,20);
//		jtfDrinksAvailable.setHorizontalAlignment(JTextField.CENTER);
//		jtfDrinksAvailable.setForeground(Color.yellow);
//		jtfDrinksAvailable.setBackground(Color.black);
//
//		JLabel jlBrandPrice = new JLabel("Brand Price");
//		jlBrandPrice.setBounds(300,620,100,20);
//		jlBrandPrice.setFont(new Font("",1,15));
//		JTextField jtfBrandPrice = new JTextField(drinkMoney);
//		jtfBrandPrice.setBounds(400,620,80,20);
//
//		jtfBrandPrice.addKeyListener(new KeyAdapter(){
//			public void keyTyped(KeyEvent brandModifyEvent) {
//				char brandNewPrice = brandModifyEvent.getKeyChar();
//				String brandNewPriceEntered = jtfBrandPrice.getText();
//				if(brandNewPrice == KeyEvent.VK_ENTER) {
//					System.out.println(brandNewPriceEntered);
//					List<String[]> list = new ArrayList<>();
//					String line = null;
//					try {
//						BufferedReader reader = new BufferedReader(new FileReader("./Data/dwd_drink_info.csv"));
//
//
//						while ((line=reader.readLine())!=null) {
//							list.add(line.split(","));
//						}
//						reader.close();
//
//						for(int i = 0; i < list.size() ; i++) {
//							if(drinkLine != null && i == Integer.parseInt(drinkLine)) {
//								list.get(i)[3] = brandNewPriceEntered;
//								System.out.println(brandNewPriceEntered);
//							}
//						};
//
//						BufferedWriter writer = new BufferedWriter(new FileWriter("./Data/dwd_drink_info.csv"));
//
//						for (int i = 0; i < list.size(); i++) {
//							for (int j = 0; j < list.get(i).length; j++) {
//								writer.write(list.get(i)[j].toString() + ",");
//								if (j == list.get(i).length - 1) {
//									writer.newLine();
//								}
//							}
//						}
//						writer.close();
//
//					} catch (FileNotFoundException e) {
//						e.printStackTrace();
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
//				};
//			}
//		});
//
//
//
//		try {
//			BufferedReader drinksReader = new BufferedReader(new FileReader("./Data/dwd_drink_info.csv"));
//			String line = null;
//			int lineIndex = 0;
//
//			while((line=drinksReader.readLine())!=null) {
//				String item1[] = line.split(",");//���ݶ���
//				if(lineIndex > 0) {
//					JButton jbDrink = new JButton(item1[1]);
//					jbDrink.setBackground(Color.white);
//					jpDrinks.add(jbDrink);
//
//					jbDrink.addActionListener(new ActionListener() {
//						@Override
//						public void actionPerformed(ActionEvent arg0) {
//							try {
//								BufferedReader drinksReader2 = new BufferedReader(new FileReader("./Data/dwd_drink_info.csv"));
//								String line2 = null;
//								while((line2=drinksReader2.readLine())!=null) {
//									String item2[] = line2.split(",");
//									if(item2[1].equals(((JButton)arg0.getSource()).getText())) {
//										//jbDrink.setBorder(BorderFactory.createLineBorder(Color.red));
//										drinkLine = item2[0];
//										drinkAvailable = item2[2];
//										drinkMoney = item2[3];
//										drinkSelected = ((JButton)arg0.getSource()).getText();
//										jtfDrinksAvailable.setText(drinkAvailable);
//										jtfBrandPrice.setText(drinkMoney);
//									}
//									System.out.println(drinkLine);
//									System.out.println(drinkSelected);
//									System.out.println(drinkAvailable);
//									System.out.println(drinkMoney);
//								}
//								drinksReader2.close();
//							} catch (IOException e) {
//								e.printStackTrace();
//							}
//						}
//					});
//				}
//				lineIndex++;
//			}
//			drinksReader.close();
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//
//		maintainerContainer.add(jlDrinksAvailable);
//		maintainerContainer.add(jpDrinks);
//		maintainerContainer.add(jtfDrinksAvailable);
//		maintainerContainer.add(jlBrandPrice);
//		maintainerContainer.add(jtfBrandPrice);
//
//
//
//		JButton jbShowTotal = new JButton("Show Total Cash Held");
//		JTextField jtfTotalCash = new JTextField();
//
//		jbShowTotal.setBounds(200,700,300,30);
//		jbShowTotal.setFont(new Font("",1,15));
//		jbShowTotal.setBackground(Color.white);
//		jtfTotalCash.setBounds(510,705,80,20);
//		jtfTotalCash.setHorizontalAlignment(JTextField.CENTER);
//		jtfTotalCash.setForeground(Color.yellow);
//		jtfTotalCash.setBackground(Color.black);
//
//		jbShowTotal.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				jtfTotalCash.setText(String.valueOf((totalCash) + "c"));
//			}
//		});
//
//		JButton jbCollectCash = new JButton("Press to Collect All Cash");
//		JLabel jlCollectCash = new JLabel("Collect Cash:");
//		JTextField jtfCollectCash = new JTextField("0 c");
//
//		jbCollectCash.setBounds(250,750,300,30);
//		jbCollectCash.setFont(new Font("",1,15));
//		jbCollectCash.setBackground(Color.white);
//		jlCollectCash.setBounds(320,800,100,20);
//		jlCollectCash.setFont(new Font("",1,15));
//		jtfCollectCash.setBounds(430,800,50,20);
//		jtfCollectCash.setHorizontalAlignment(JTextField.CENTER);
//		jtfCollectCash.setForeground(Color.yellow);
//		jtfCollectCash.setBackground(Color.black);
//
//		jbCollectCash.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				jtfCollectCash.setText(String.valueOf((totalCash) + "c"));
//				totalCash = 0;
//
//				new NumberObserver().maintainerCollectCoins("./Data/dwd_money_stat.csv");
//			}
//		});
//
//		JButton jbFinished = new JButton("Press Here when Finished");
//		jbFinished.setBounds(240,880,300,30);
//		jbFinished.setFont(new Font("",1,15));
//		jbFinished.setBackground(Color.white);
//
//		jbFinished.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				setLogout();
//				jlCoins.setVisible(isLogin);
//				jpCoins.setVisible(isLogin);
//				jtfTotolCoins.setVisible(isLogin);
//				jlDrinksAvailable.setVisible(isLogin);
//				jpDrinks.setVisible(isLogin);
//				jtfDrinksAvailable.setVisible(isLogin);
//				jlBrandPrice.setVisible(isLogin);
//				jtfBrandPrice.setVisible(isLogin);
//				jbShowTotal.setVisible(isLogin);
//				jtfTotalCash.setVisible(isLogin);
//				jbCollectCash.setVisible(isLogin);
//				jlCollectCash.setVisible(isLogin);
//				jtfCollectCash.setVisible(isLogin);
//				jbFinished.setVisible(isLogin);
//				jbMaintainerPasswordValid.setBackground(Color.gray);
//				jbMaintainerPasswordInvalid.setBackground(Color.gray);
//				jpfMaintainerPassword.setText("");
//			}
//		});
//
//		maintainerContainer.add(jbShowTotal);
//		maintainerContainer.add(jtfTotalCash);
//		maintainerContainer.add(jbCollectCash);
//		maintainerContainer.add(jlCollectCash);
//		maintainerContainer.add(jtfCollectCash);
//		maintainerContainer.add(jbFinished);
//
//		jlCoins.setVisible(isLogin);
//		jpCoins.setVisible(isLogin);
//		jtfTotolCoins.setVisible(isLogin);
//		jlDrinksAvailable.setVisible(isLogin);
//		jpDrinks.setVisible(isLogin);
//		jtfDrinksAvailable.setVisible(isLogin);
//		jlBrandPrice.setVisible(isLogin);
//		jtfBrandPrice.setVisible(isLogin);
//		jbShowTotal.setVisible(isLogin);
//		jtfTotalCash.setVisible(isLogin);
//		jbCollectCash.setVisible(isLogin);
//		jlCollectCash.setVisible(isLogin);
//		jtfCollectCash.setVisible(isLogin);
//		jbFinished.setVisible(isLogin);
//
//		jpfMaintainerPassword.addKeyListener(new KeyAdapter(){
//			public void keyTyped(KeyEvent passwordTypeEvent) {
//				char passwordChar = passwordTypeEvent.getKeyChar();
//				char[] passwordEntered = jpfMaintainerPassword.getPassword();
//				String maintainerPassword = "123a";
//				char[] maintainerPasswordString  = maintainerPassword.toCharArray();
//				if(passwordChar == KeyEvent.VK_ENTER) {
//					if(Arrays.equals(passwordEntered, maintainerPasswordString)) {
//						setIsLogin();
//						jbMaintainerPasswordValid.setBackground(Color.green);
//						jbMaintainerPasswordInvalid.setForeground(Color.black);
//						jbMaintainerPasswordInvalid.setBackground(Color.gray);
//					}
//					else{
//						setLogout();
//						jbMaintainerPasswordValid.setBackground(Color.gray);
//						jbMaintainerPasswordInvalid.setForeground(Color.gray);
//						jbMaintainerPasswordInvalid.setBackground(Color.red);
//					}
//				};
//				jlCoins.setVisible(isLogin);
//				jpCoins.setVisible(isLogin);
//				jtfTotolCoins.setVisible(isLogin);
//				jlDrinksAvailable.setVisible(isLogin);
//				jpDrinks.setVisible(isLogin);
//				jtfDrinksAvailable.setVisible(isLogin);
//				jlBrandPrice.setVisible(isLogin);
//				jtfBrandPrice.setVisible(isLogin);
//				jbShowTotal.setVisible(isLogin);
//				jtfTotalCash.setVisible(isLogin);
//				jbCollectCash.setVisible(isLogin);
//				jlCollectCash.setVisible(isLogin);
//				jtfCollectCash.setVisible(isLogin);
//				jbFinished.setVisible(isLogin);
//			}
//		});
//
//		maintainerContainer.setLayout(null);
//
//		maintainerJF.setVisible(true);
//		maintainerJF.setSize(800,1000);
//
//		maintainerJF.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//	}
//
//}

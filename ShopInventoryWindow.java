package FinalPT;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.*;
import javax.swing.*;
import java.awt.*;

public class ShopInventoryWindow extends TextFileManager {

    String inName, inType, inProduct, inExp_Date, inQuantity, inProduct_ID;
    ArrayList<String> result;
    ArrayList<Integer> storedQuantity = new ArrayList<>();

    boolean canEdit = true, exists = false;
    int buttonsize = 101;
    int cnt = 0;

    JComboBox<String> combobox = new JComboBox<>();
    JTextField StatusModeBody;
    JLabel StatusModeHeader;
    JButton addbutton,button;

    JPanel LeftPanel = new JPanel();
    JPanel row1panel = new JPanel();
    JPanel row2panel = new JPanel();
    JPanel row3panel = new JPanel();
    JPanel row4panel = new JPanel();
    JPanel row5panel = new JPanel();
    JPanel row6panel = new JPanel();
    JPanel footerpanel = new JPanel();

    JLabel productNameLabel = new JLabel("NAME:            ");
    JTextField productNameTextField = new JTextField(15);
    JLabel productTypeLabel = new JLabel("TYPE:             ");
    JTextField productTypeTextField = new JTextField(15);
    JLabel productProductLabel = new JLabel("PRODUCT:    ");
    JTextField productProductTextField = new JTextField(15);
    JLabel productEXPDATELabel = new JLabel("EXP.DATE:     ");
    JTextField productEXPDATETextField = new JTextField(15);
    JLabel productQuantityLabel = new JLabel("QUANTITY:   ");
    JTextField productQuantityTextField = new JTextField(15);
    JLabel productIDLabel = new JLabel("PRODUCT ID:");
    JTextField productIDTextField = new JTextField(15);

    JPanel RightPanel = new JPanel();
    JPanel RightPanelFooter = new JPanel();

    JScrollPane scrollPane = new JScrollPane(RightPanel);
    JSplitPane innersplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, scrollPane, RightPanelFooter);
    JSplitPane outersplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, LeftPanel, innersplitPane);

    void shopInventoryWindow() {

            JFrame window = new JFrame(" Inventory Tracker ");
            final int winheight = 440, winwidth = 600;

            window.setDefaultCloseOperation(window.EXIT_ON_CLOSE);
            window.setUndecorated(false);
            window.setResizable(false);
            window.setSize(new Dimension(winwidth, winheight));
            window.setLocationRelativeTo(null);

            try {
                window.setIconImage((Image) ImageIO.read(getClass().getResourceAsStream("icon.png")));
            } catch (IOException e) {
                e.printStackTrace();
            }

            LeftPanel.setLayout(new BoxLayout(LeftPanel, BoxLayout.Y_AXIS));
            LeftPanel.setBackground(Color.WHITE);

            row1panel.add(productNameLabel);
            row1panel.add(productNameTextField);
            row2panel.add(productTypeLabel);
            row2panel.add(productTypeTextField);
            row3panel.add(productProductLabel);
            row3panel.add(productProductTextField);
            row4panel.add(productEXPDATELabel);
            row4panel.add(productEXPDATETextField);
            row5panel.add(productQuantityLabel);
            row5panel.add(productQuantityTextField);
            row6panel.add(productIDLabel);
            row6panel.add(productIDTextField);

            combobox.addItem("List of Products:");

            combobox.setEditable(false);
            productNameTextField.setEditable(false);
            productTypeTextField.setEditable(false);
            productProductTextField.setEditable(false);
            productEXPDATETextField.setEditable(false);
            productQuantityTextField.setEditable(false);
            productIDTextField.setEditable(false);

            footerpanel.setLayout(new GridLayout(2, 1, 1, 1));

            String status = "N\\A", productlowquantity = "N\\A";
            JLabel StatusLabel = new JLabel("            QUANTITY: " + status + "  ");
            StatusLabel.setFont(new Font("Arial", Font.BOLD, 18));
            StatusLabel.setOpaque(true);
            StatusLabel.setBackground(Color.white);
            StatusLabel.setForeground(Color.BLACK);

            JLabel ProductwithlowQuantity = new JLabel("               Low Quantity: " + productlowquantity);
            ProductwithlowQuantity.setFont(new Font("Arial", Font.BOLD, 14));
            ProductwithlowQuantity.setForeground(Color.BLACK);

            footerpanel.add(StatusLabel);
            footerpanel.add(ProductwithlowQuantity);

            RightPanel.setBackground(Color.lightGray);
            RightPanel.setLayout(new GridLayout(0, 3, 1, 1));

            addbutton = new JButton();
            addbutton.setPreferredSize(new Dimension(buttonsize, buttonsize));
            addbutton.setText("+");
            addbutton.setFont(new Font("Arial", Font.BOLD, buttonsize));
            addbutton.setVisible(false);

            addbutton.addActionListener(e -> {

                productNameTextField.setText("");
                productTypeTextField.setText("");
                productProductTextField.setText("");
                productEXPDATETextField.setText("");
                productQuantityTextField.setText("");
                productIDTextField.setText("");

                System.out.println();
                System.out.println("canEdit == " + canEdit);
                button = new JButton("P#" + cnt);
                button.putClientProperty("P#" + cnt, cnt);
                button.setPreferredSize(new Dimension(buttonsize, buttonsize));

                System.out.println("SYSTEM: new Button(B#" + cnt + ") added.");
                System.out.println();

                cnt++;
                int indexnumber = cnt;

                button.addActionListener(e1 -> {

                    System.out.println();
                    System.out.println("canEdit == " + canEdit);
                    System.out.println("SYSTEM: Pressed inner Button, P#" + indexnumber);

                    // Assign the String values in the TextArea into a String variable, so that I can retrieve it later
                    inName = productNameTextField.getText();
                    inType = productTypeTextField.getText();
                    inProduct = productProductTextField.getText();
                    inExp_Date = productEXPDATETextField.getText();
                    inQuantity = productQuantityTextField.getText();
                    inProduct_ID = productIDTextField.getText();

                    if (!canEdit) {
                        int response = JOptionPane.showConfirmDialog(
                                null,
                                "Do you want to write file(Overwrite)?",
                                "Confirm Action",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE);

                        if (response == JOptionPane.YES_OPTION) {
                            System.out.println("SYSTEM: user picked YES...");
                            bufferedWrite("Data#" + indexnumber + ".txt", inName, inType, inProduct, inExp_Date, inQuantity, inProduct_ID);
                            for (int i = 0; i < combobox.getItemCount(); i++) {
                                if (inName.equals(combobox.getItemAt(i))) {
                                    exists = true;
                                }
                            }

                            if (!exists) {
                                combobox.addItem("P#" + (indexnumber - 1) + ": " + inName);
                            }

                            button.setForeground(Color.GRAY);

                        } else if (response == JOptionPane.NO_OPTION) {

                            System.out.println("SYSTEM: user picked NO...");
                            JOptionPane.showMessageDialog(null, "Viewing file content.");

                            LeftPanel.revalidate();
                            System.out.println("SYSTEM: Reading file Data#" + indexnumber + ".txt");

                            result = bufferedReader("Data#" + indexnumber + ".txt");

                            productNameTextField.setText(result.get(0));
                            productTypeTextField.setText(result.get(1));
                            productProductTextField.setText(result.get(2));
                            productEXPDATETextField.setText(result.get(3));
                            productQuantityTextField.setText(result.get(4));
                            productIDTextField.setText(result.get(5));

                            int a = Integer.parseInt(result.get(4).trim());
                            if (a <= 10) {
                                StatusLabel.setText("            QUANTITY: Low");
                                StatusLabel.setForeground(Color.RED);
                            } else if (a > 10) {
                                StatusLabel.setText("       QUANTITY: Sufficient");
                                StatusLabel.setForeground(Color.GREEN);
                            } else {
                                StatusLabel.setText("                 QUANTITY: N/A");
                            }

                            int parsedIntinQuantity = Integer.parseInt(result.get(4).trim());

                            if (!storedQuantity.contains(parsedIntinQuantity)) {
                                storedQuantity.add(parsedIntinQuantity);
                            }

                            if (storedQuantity.isEmpty()) {

                                System.out.println("SYSTEM: The ArrayList 'storedQuantity' is empty.");

                            } else {

                                System.out.println();
                                Integer minValue = Collections.min(storedQuantity);
                                int minIndex = storedQuantity.indexOf(minValue);

                                System.out.println("SYSTEM: The lowest value is:" + minValue);
                                System.out.println("SYSTEM: The index of the lowest value is: " + minIndex);
                                ProductwithlowQuantity.setText("                Low Quantity: P#" + minIndex);
                                System.out.println(storedQuantity);
                                System.out.println();

                            }

                            result.clear();

                            RightPanel.revalidate();
                            RightPanel.repaint();
                            LeftPanel.revalidate();
                            LeftPanel.repaint();

                        } else {
                            System.out.println("SYSTEM: Dialog Closed.");
                        }

                    } else if (canEdit) {

                        LeftPanel.revalidate();
                        System.out.println("SYSTEM: Reading file Data#" + indexnumber + ".txt");

                        result = bufferedReader("Data#" + indexnumber + ".txt");

                        productNameTextField.setText(result.get(0));
                        productTypeTextField.setText(result.get(1));
                        productProductTextField.setText(result.get(2));
                        productEXPDATETextField.setText(result.get(3));
                        productQuantityTextField.setText(result.get(4));
                        productIDTextField.setText(result.get(5));

                        int a = Integer.parseInt(result.get(4).trim());
                        if (a <= 10) {
                            StatusLabel.setText("            QUANTITY: Low");
                            StatusLabel.setForeground(Color.RED);
                        } else if (a > 10) {
                            StatusLabel.setText("       QUANTITY: Sufficient");
                            StatusLabel.setForeground(Color.GREEN);
                        } else {
                            StatusLabel.setText("                 QUANTITY: N/A");
                        }

                        int parsedIntinQuantity = Integer.parseInt(result.get(4).trim());

                        if (!storedQuantity.contains(parsedIntinQuantity)) {
                            storedQuantity.add(parsedIntinQuantity);
                        }

                        if (storedQuantity.isEmpty()) {

                            System.out.println("SYSTEM: The ArrayList 'storedQuantity' is empty.");

                        } else {

                            System.out.println();
                            Integer minValue = Collections.min(storedQuantity);
                            int minIndex = storedQuantity.indexOf(minValue);

                            System.out.println("SYSTEM: The lowest value is:" + minValue);
                            System.out.println("SYSTEM: The index of the lowest value is: " + minIndex);
                            ProductwithlowQuantity.setText("                Low Quantity: P#" + minIndex);
                            System.out.println(storedQuantity);
                            System.out.println();

                        }

                        result.clear();

                        RightPanel.revalidate();
                        LeftPanel.revalidate();
                    }

                    LeftPanel.revalidate();
                    RightPanel.revalidate();

                });

                RightPanel.add(button);
                RightPanel.revalidate();

            });

            RightPanel.add(addbutton);

            RightPanelFooter.setLayout(null);

            StatusModeHeader = new JLabel("      MODE: ");
            StatusModeHeader.setFont(new Font("Arial", Font.BOLD, 18));
            StatusModeHeader.setOpaque(true);
            StatusModeHeader.setBackground(Color.white);
            StatusModeHeader.setBounds(0, 4, 375, 35);

            StatusModeBody = new JTextField("VIEWER");
            StatusModeBody.setEditable(false);
            StatusModeBody.setBounds(30, 45, 200, 25);

            JButton modeSwitch = new JButton("M");
            modeSwitch.setFont(new Font("Arial", Font.BOLD, 12));
            modeSwitch.setBounds(270, 40, 46, 46);
            modeSwitch.addActionListener(e -> {
                System.out.println("SYSTEM: canEdit == " + canEdit);
                System.out.println();

                if (canEdit) {

                    StatusModeBody.setText("EDITOR");
                    productNameTextField.setEditable(true);
                    productTypeTextField.setEditable(true);
                    productProductTextField.setEditable(true);
                    productEXPDATETextField.setEditable(true);
                    productQuantityTextField.setEditable(true);
                    productIDTextField.setEditable(true);
                    addbutton.setVisible(true);
                    RightPanel.revalidate();
                    LeftPanel.revalidate();
                    RightPanel.repaint();
                    RightPanel.add(addbutton, 0);
                    canEdit = false;

                } else if (!canEdit) {

                    StatusModeBody.setText("READER");
                    productNameTextField.setEditable(false);
                    productTypeTextField.setEditable(false);
                    productProductTextField.setEditable(false);
                    productEXPDATETextField.setEditable(false);
                    productQuantityTextField.setEditable(false);
                    productIDTextField.setEditable(false);
                    addbutton.setVisible(false);
                    RightPanel.revalidate();
                    LeftPanel.revalidate();
                    RightPanel.repaint();
                    RightPanel.remove(addbutton);
                    canEdit = true;

                }

            });

            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

            innersplitPane.setOneTouchExpandable(false);
            innersplitPane.setEnabled(false);
            innersplitPane.setDividerLocation(310);

            outersplitPane.setOneTouchExpandable(false);
            outersplitPane.setEnabled(false);
            outersplitPane.setDividerLocation(260);

            LeftPanel.add(Box.createRigidArea(new Dimension(3, 3)));
            LeftPanel.add(combobox);
            LeftPanel.add(row1panel);
            LeftPanel.add(row2panel);
            LeftPanel.add(row3panel);
            LeftPanel.add(row4panel);
            LeftPanel.add(row5panel);
            LeftPanel.add(row6panel);
            LeftPanel.add(footerpanel);
            LeftPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            LeftPanel.revalidate();

            RightPanelFooter.add(modeSwitch);
            RightPanelFooter.add(StatusModeBody);
            RightPanelFooter.add(StatusModeHeader);
            RightPanelFooter.revalidate();

            window.add(outersplitPane);
            window.revalidate();
            window.setVisible(true);

        }
/*
    public void main(String[] args) {
        shopInventoryWindow();
    }

 */


}
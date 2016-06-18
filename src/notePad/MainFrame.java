package notePad;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;

import com.sun.glass.events.KeyEvent;

public class MainFrame extends JFrame {

	private String filePath;
	// Create Menus
	private JMenuBar mainMenuBar;
	private JMenu file;
	private JMenu edit;
	private JMenu help;
	private JMenuItem newItem;
	private JMenuItem openItem;
	private JMenuItem saveItem;
	private JMenuItem saveAsItem;
	private JMenuItem exitItem;
	private JMenuItem copyItem;
	private JMenuItem aboutItem;
	// create tool bar

	// Create File Chooser
	private JFileChooser fileChooser = new JFileChooser();

	// create textArea
	private JTextArea mainTextArea = new JTextArea();
	// create scrollPane
	JScrollPane mainTextScroll = new JScrollPane(mainTextArea);

	// create layout
	BorderLayout mainLayout = new BorderLayout();
	FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT);

	/**
	 * @param null
	 * @return null
	 * @save files
	 */
	private void saving() {
		fileChooser.showSaveDialog(null);
		File file1 = fileChooser.getSelectedFile();
		filePath = file1.getAbsolutePath();
		try {
			FileWriter writer = new FileWriter(filePath);
			mainTextArea.write(writer);
			writer.close();
			JOptionPane.showMessageDialog(null, "Saved Sucessfully");
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, e1);
		}
	}

	MainFrame() {
		super("NotePad");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// make a toolbar
		JToolBar bar = new JToolBar();
		bar.setLayout(flowLayout);
		JButton saveBtn = new JButton("Save");
		bar.add(saveBtn);

		// Allocation
		mainMenuBar = new JMenuBar();
		file = new JMenu("File");
		edit = new JMenu("Edit");
		help = new JMenu("Help");
		newItem = new JMenuItem("New");
		openItem = new JMenuItem("Open");
		saveItem = new JMenuItem("Save");
		saveAsItem = new JMenuItem("Save as");
		exitItem = new JMenuItem("Exit");
		copyItem = new JMenuItem("Copy All");
		aboutItem = new JMenuItem("About");

		// add toolbar
		add(bar, mainLayout.SOUTH);

		// Add Menus

		setJMenuBar(mainMenuBar);
		mainMenuBar.add(file);
		mainMenuBar.add(edit);
		mainMenuBar.add(help);
		file.add(newItem);
		file.add(openItem);
		file.add(saveItem);
		file.add(saveAsItem);
		file.addSeparator();
		file.add(exitItem);
		edit.add(copyItem);
		help.add(aboutItem);

		// Allocation

		// add textArea
		add(mainTextScroll, mainLayout.CENTER);

		// Mnemonic
		file.setMnemonic(KeyEvent.VK_F);
		edit.setMnemonic(KeyEvent.VK_E);
		help.setMnemonic(KeyEvent.VK_H);

		// Accelerator
		newItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
				ActionEvent.CTRL_MASK));
		openItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
				ActionEvent.CTRL_MASK));
		saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				ActionEvent.CTRL_MASK));
		exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
				ActionEvent.CTRL_MASK));

		// ActionListener
		newItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mainTextArea.setText(null);

			}
		});

		openItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fileChooser.showOpenDialog(null);
				File file1 = fileChooser.getSelectedFile();
				filePath = file1.getAbsolutePath();
				try {
					FileReader reader = new FileReader(filePath);
					BufferedReader buffer = new BufferedReader(reader); // bufferedReader
																		// seeking
																		// efficiency
					mainTextArea.read(buffer, null);
					buffer.close();

				} catch (Exception e1) {

					JOptionPane.showMessageDialog(null, e1);
				}

			}
		});
		saveItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (filePath != null) {

					try {
						FileWriter writer = new FileWriter(filePath);
						mainTextArea.write(writer);
						writer.close();
					} catch (IOException e1) {

						JOptionPane.showMessageDialog(null, e1);
					}
				} else {
					saving();

				}

			}
		});
		saveAsItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				saving();

			}
		});
		exitItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);

			}
		});
		copyItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				StringSelection stringSelection = new StringSelection(
						mainTextArea.getText());
				java.awt.datatransfer.Clipboard clpbrd = Toolkit
						.getDefaultToolkit().getSystemClipboard();
				clpbrd.setContents(stringSelection, null);

			}
		});
		aboutItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				JOptionPane
						.showMessageDialog(
								null,
								"Created By Ahmed Fahmi \n www.facebook.com\\ahmedfahmi",
								"About the Author", JOptionPane.PLAIN_MESSAGE);
			}
		});
		saveBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				saving();

			}
		});

	}

}

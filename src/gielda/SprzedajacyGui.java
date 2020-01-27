
package gielda;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SprzedajacyGui extends JFrame {

	private AgentSprzedajacy agentSprzedajacy;

	private JTextField jTextFieldMarka;
	private JTextField jTextFieldModel;
	private JTextField jTextFieldTypNadwozia;
	private JTextField jTextFieldTypSilnika;
	private JTextField jTextFieldPojemnoscSilnika;
	private JTextField jTextFieldRokProdukcji;
	private JTextField jTextFieldCena;
	private JTextField jTextFieldKosztyDodatkowe;
	public JComboBox<String> jComboBox;

	public SprzedajacyGui(AgentSprzedajacy agent) {
		super(agent.getLocalName());
		agentSprzedajacy = agent;
		JPanel jPanel = new JPanel();

		jPanel.setLayout(new GridLayout(8, 2));
		jPanel.add(new JLabel("Marka:"));
		jTextFieldMarka = new JTextField(15);
		jPanel.add(jTextFieldMarka);
		jPanel.add(new JLabel("Model:"));
		jTextFieldModel = new JTextField(15);
		jPanel.add(jTextFieldModel);
		jPanel.add(new JLabel("Typ Nadwozia:"));
		jTextFieldTypNadwozia = new JTextField(15);
		jPanel.add(jTextFieldTypNadwozia);
		jPanel.add(new JLabel("Typ silnika:"));
		jTextFieldTypSilnika = new JTextField(15);
		jPanel.add(jTextFieldTypSilnika);
		jPanel.add(new JLabel("Pojemnoœæ silnika:"));
		jTextFieldPojemnoscSilnika = new JTextField(15);
		jPanel.add(jTextFieldPojemnoscSilnika);
		jPanel.add(new JLabel("Rok Produkcji:"));
		jTextFieldRokProdukcji = new JTextField(15);
		jPanel.add(jTextFieldRokProdukcji);
		jPanel.add(new JLabel("Cena:"));
		jTextFieldCena = new JTextField(15);
		jPanel.add(jTextFieldCena);
		jPanel.add(new JLabel("Koszty dodatkowe:"));
		jTextFieldKosztyDodatkowe = new JTextField(15);
		jPanel.add(jTextFieldKosztyDodatkowe);
		jComboBox = new JComboBox<>();

		getContentPane().add(jPanel, BorderLayout.CENTER);
		getContentPane().add(jComboBox, BorderLayout.NORTH);

		JButton jButtonDodajAuto = new JButton("Dodaj auto");
		jButtonDodajAuto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				try {
					String marka = jTextFieldMarka.getText();
					String model = jTextFieldModel.getText();
					String typNadwozia = jTextFieldTypNadwozia.getText();
					String typSilnika = jTextFieldTypSilnika.getText();
					int pojemnoscSilnika = Integer.parseInt(jTextFieldPojemnoscSilnika.getText());
					int rokProdukcji = Integer.parseInt(jTextFieldRokProdukcji.getText());
					int cena = Integer.parseInt(jTextFieldCena.getText());
					int kosztyDodatkowe = Integer.parseInt(jTextFieldKosztyDodatkowe.getText());

					Auto auto = new Auto(marka, model, typNadwozia, typSilnika,
							pojemnoscSilnika, rokProdukcji, cena, kosztyDodatkowe);
					agentSprzedajacy.dodajAuto(auto);
					jComboBox.addItem(auto.toString());
					jComboBox.setSelectedItem(auto.toString());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		jPanel = new JPanel();
		jPanel.add(jButtonDodajAuto);

		JButton jButtonOdswiez = new JButton("Odswiez");
		jButtonOdswiez.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				try {
					jComboBox = new JComboBox<String>();

					Vector<Auto> v = agentSprzedajacy.getAutaSprzedajacego();

					for (Auto auto : v) {
						jComboBox.addItem(auto.toString());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		jPanel.add(jButtonOdswiez);

		getContentPane().add(jPanel, BorderLayout.SOUTH);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				agentSprzedajacy.doDelete();
			}
		});

		setResizable(false);
	}

	public void showGui() {
		pack();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int centerX = (int) screenSize.getWidth() / 2;
		int centerY = (int) screenSize.getHeight() / 2;
		setLocation(centerX - getWidth() / 2, centerY - getHeight() / 2);
		super.setVisible(true);
	}
}

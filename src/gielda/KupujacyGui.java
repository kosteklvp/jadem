package gielda;

import java.awt.BorderLayout;
import java.awt.Dimension;
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

public class KupujacyGui extends JFrame {
	private AgentKupujacy agentKupujacy;

	public JLabel jLabelHajs;
	public JComboBox<String> jComboBox;

	public KupujacyGui(AgentKupujacy agent) {
		super(agent.getLocalName());
		agentKupujacy = agent;
		jComboBox = new JComboBox<>();
		jLabelHajs = new JLabel(agentKupujacy.getHajs());
		jLabelHajs.setSize(200, 16);

		getContentPane().add(jLabelHajs, BorderLayout.CENTER);
		getContentPane().add(jComboBox, BorderLayout.NORTH);

		JButton jButtonOdswiez = new JButton(
				"                                                       Odswiez                                                       ");
		jButtonOdswiez.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				try {
					jLabelHajs = new JLabel(agentKupujacy.getHajs());
					jComboBox = new JComboBox<String>();

					Vector<Auto> v = agentKupujacy.getAutaKupujacego();

					for (Auto auto : v) {
						jComboBox.addItem(auto.toString());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		getContentPane().add(jButtonOdswiez, BorderLayout.SOUTH);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				agentKupujacy.doDelete();
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

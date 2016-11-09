package code.view;

import code.model.Calculator;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
public class CalculatorUI {
	
	private Calculator _calc;
	
	private JFrame _window;
	
	private JPanel _panel1;
	
	private JPanel _panel2;
	
	private JLabel _score;

	private JCheckBox  _beacon;

	private JSpinner _particleInCenter;
	
	private JSpinner _particleInCorner;
	
	public CalculatorUI(){
		_calc = new Calculator(this);
		_window = new JFrame("FTC Velocity Vortex Score Calculator");
		_window.getContentPane().setLayout(new BoxLayout(_window.getContentPane(), BoxLayout.Y_AXIS));
		
		_panel1 = new JPanel();
		_panel1.setLayout(new BoxLayout(_panel1, BoxLayout.X_AXIS));
		
		JLabel firstLogo = new JLabel();
		firstLogo.setIcon(new ImageIcon("Images/ftc-2016-post-kickoff-promo.png"));
		_panel1.add(firstLogo);
		
		JLabel title = new JLabel();
		title.setFont(new Font("Helvetica",Font.BOLD, 24));
		title.setText("FTC VELOCITY VORTEX SCORE CALCULATOR");
		_panel1.add(title);
		
		JLabel mohonLogo = new JLabel();
		mohonLogo.setIcon(new ImageIcon("Images/mohon.png"));
		_panel1.add(mohonLogo);
		
		_panel2 = new JPanel();
		_panel2.setLayout(new BoxLayout(_panel2, BoxLayout.X_AXIS));
		
		JPanel scorePanel = new JPanel(new GridBagLayout());
		GridBagConstraints sLayout = new GridBagConstraints();
		sLayout.gridx = 0;
		sLayout.gridy = 0;
		sLayout.anchor = GridBagConstraints.LINE_START;
		sLayout.weightx = 0.5;
		
		JLabel auton = new JLabel("Autonomous: ");
		auton.setFont(new Font("Helvetica", Font.BOLD, 14));
		scorePanel.add(auton, sLayout);
		
		_beacon = new JCheckBox("Beacon Lit");
		_beacon.addItemListener(new ItemListener(){

			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED){
					_calc.beaconLit(true);
				}
				else{
					_calc.beaconLit(false);
				}
			}
			
		});
		
		JPanel spinnerPanel = new JPanel();
		JLabel spinnerText = new JLabel("Number of Particles in Center Vortex: ");
		_particleInCenter = new JSpinner();
		_particleInCenter.setModel(new SpinnerNumberModel(0,0,2,1));
		_particleInCenter.addChangeListener(new ChangeListener(){

			@Override
			public void stateChanged(ChangeEvent e) {
				JSpinner source = (JSpinner)e.getSource();
				_calc.picChanged((int)source.getValue());
			}
			
		});
		
		spinnerPanel.add(spinnerText);
		spinnerPanel.add(_particleInCenter);
		
		JPanel cornerPanel = new JPanel();
		JLabel cornerText = new JLabel("Number of Particles in Corner Vortex: ");
		_particleInCorner = new JSpinner();
		_particleInCorner.setModel(new SpinnerNumberModel(0,0,2,1));
		_particleInCorner.addChangeListener(new ChangeListener(){

			@Override
			public void stateChanged(ChangeEvent e) {
				JSpinner source = (JSpinner)e.getSource();
				_calc.cornChanged((int)source.getValue());
			}
			
		});
		cornerPanel.add(cornerText);
		cornerPanel.add(_particleInCorner);
		
		sLayout.gridx = 0;
		sLayout.gridy = 1;
		scorePanel.add(_beacon, sLayout);
		
		sLayout.gridx = 1;
		sLayout.gridy = 1;
		scorePanel.add(spinnerPanel, sLayout);
		
		sLayout.gridx = 0;
		sLayout.gridy = 2;
		scorePanel.add(cornerPanel, sLayout);
		
		_panel2.add(scorePanel);
		
		JButton clearScore = new JButton("CLEAR SCORE");
		clearScore.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				_calc.clearScoreKeyPressed();
			}
		});
		
		_score = new JLabel("Team Score: " + _calc.getScore());
		
		JPanel clearPanel = new JPanel();
		clearPanel.setLayout(new GridLayout(2,1));
		clearPanel.add(_score);
		clearPanel.add(clearScore);
		
		
		
		
		
		
		_window.getContentPane().add(_panel1);
		_window.getContentPane().add(_panel2);
		_window.getContentPane().add(clearPanel);
		_window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		_window.setVisible(true);
		_window.pack();
	}
	public void update(){
		_score.setText("Team Score: " + _calc.getScore());
		_window.repaint();
	}
	public void clearBoxes(){
		_beacon.setSelected(false);
		_particleInCenter.setValue(0);
		_particleInCorner.setValue(0);
	}
}

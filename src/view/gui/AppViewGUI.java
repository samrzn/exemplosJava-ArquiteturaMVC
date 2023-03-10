package view.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import controller.ContatoController;
import model.Contato;

public class AppViewGUI extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;

	private ContatoController ctrl = new ContatoController();
	private Contato ct = new Contato();

	private TableModel getTableModel() {
		List<Contato> lista = ctrl.obterTodos();
		Object[][] dtb = new Object[lista.size()][3];
		for (int i = 0; i < lista.size(); i++) {
			dtb[i][0] = lista.get(i).getId();
			dtb[i][1] = lista.get(i).getNome();
			dtb[i][2] = lista.get(i).getIdade();
		}
		return new DefaultTableModel(dtb, new String[] { "Id", "Nome", "Idade"});
	}

	private void preencherPainel() {
		jLabId.setText("Id: " + ct.getId());
		txtNome.setText(ct.getNome());
		spnIdade.setValue(ct.getIdade());
	}

	public AppViewGUI() {
		initComponents();
		initEventos();
	}

	private void initEventos() {
		tblCts.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent ev) {
				super.mouseClicked(ev);
				int row = tblCts.rowAtPoint(ev.getPoint());
				TableModel modelo = tblCts.getModel();
				if (row >= 0) {
					ct.setId((int) modelo.getValueAt(row, 0));
					ct.setNome((String) modelo.getValueAt(row, 1));
					ct.setIdade((int) modelo.getValueAt(row, 2));
					preencherPainel();
				}
			}
		});

		btnNovo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {
				ct.setId(0);
				ct.setNome("");
				ct.setIdade(0);
				preencherPainel();
			}
		});

		btnSalvar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {
				ct.setNome(txtNome.getText());
				ct.setIdade((int) spnIdade.getValue());
				if (ct.getId() == 0)
					try {
						ctrl.inserirContato(ct);
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(AppViewGUI.this, ex.getMessage(), "Erro na inclusão.",
								JOptionPane.ERROR_MESSAGE);
					}
				else
					try {
						ctrl.alterarContato(ct);
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(AppViewGUI.this, ex.getMessage(), "Erro na alteração.",
								JOptionPane.ERROR_MESSAGE);
					}
				tblCts.setModel(getTableModel());
			}
		});

		btnExcluir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {
				if (ct.getId() != 0) {
					try {
						ctrl.excluirContato(ct.getId());
						tblCts.setModel(getTableModel());
						ct.setId(0);
						ct.setNome("");
						ct.setIdade(0);
						preencherPainel();
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(AppViewGUI.this, ex.getMessage(), "Erro na exclusão.",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		btnAgendamentos.addActionListener((ActionEvent ev) -> {
			if (ct.getId() == 0)
				return;
			java.awt.EventQueue.invokeLater(new Runnable() {
				public void run() {
					JanelaAgenda dialog;
					try {
						dialog = new JanelaAgenda(ct.getId(), AppViewGUI.this/* new javax.swing.JFrame() */, true);
						dialog.addWindowListener(new java.awt.event.WindowAdapter() {
							@Override
							public void windowClosing(java.awt.event.WindowEvent ev) {
								dialog.setVisible(false);
							}
						});
						dialog.setVisible(true);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			});
		});
	}

	private void initComponents() {
		jScrollPane1 = new JScrollPane();
		tblCts = new JTable();
		jPanel1 = new JPanel();
		jLabId = new JLabel();
		jLab2 = new JLabel();
		txtNome = new JTextField();
		jLab3 = new JLabel();
		spnIdade = new JSpinner();
		btnNovo = new JButton();
		btnExcluir = new JButton();
		btnSalvar = new JButton();
		btnAgendamentos = new JButton();

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		tblCts.setModel(getTableModel());
		jScrollPane1.setViewportView(tblCts);

		jPanel1.setBorder(BorderFactory.createEtchedBorder());

		jLab2.setText("Nome");
		jLab3.setText("Idade");
		btnNovo.setText("NOVO");
		btnExcluir.setText("EXCLUIR");
		btnSalvar.setText("SALVAR");
		btnAgendamentos.setText("AGENDA");
		btnAgendamentos.setBackground(Color.RED);
		preencherPainel();

		GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addContainerGap()
						.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(jLabId)
								.addGroup(jPanel1Layout.createSequentialGroup()
										.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addComponent(jLab2).addComponent(jLab3))
										.addGap(26, 26, 26)
										.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, 236,
														GroupLayout.PREFERRED_SIZE)
												.addGroup(jPanel1Layout.createSequentialGroup()
														.addComponent(spnIdade, GroupLayout.PREFERRED_SIZE, 89,
																GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 177,
																Short.MAX_VALUE)
														.addComponent(btnNovo, GroupLayout.PREFERRED_SIZE, 85,
																GroupLayout.PREFERRED_SIZE)))))
						.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(btnSalvar, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(btnExcluir, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(btnAgendamentos, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
						.addGap(10, 10, 10)));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addGap(17, 17, 17).addComponent(jLabId)
						.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(jLab2)
								.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLab3)
								.addComponent(spnIdade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(btnNovo).addComponent(btnSalvar).addComponent(btnExcluir)
								.addComponent(btnAgendamentos))
						.addContainerGap(17, Short.MAX_VALUE)));

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addContainerGap()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(jScrollPane1)
						.addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addContainerGap()
				.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 281, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addContainerGap()));

		pack();
	}

	public static void main(String[] args) {
		try {
			for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new AppViewGUI().setVisible(true);
			}
		});

	}

	private javax.swing.JButton btnExcluir;
	private javax.swing.JButton btnNovo;
	private javax.swing.JButton btnSalvar;
	private javax.swing.JButton btnAgendamentos;
	private javax.swing.JLabel jLab2;
	private javax.swing.JLabel jLab3;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTable tblCts;
	private javax.swing.JLabel jLabId;
	private javax.swing.JSpinner spnIdade;
	private javax.swing.JTextField txtNome;

} 
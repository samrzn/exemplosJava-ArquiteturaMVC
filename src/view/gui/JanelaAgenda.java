package view.gui;

import java.awt.event.ActionEvent;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.JFrame;
import javax.swing.ListModel;

import controller.CompromissoController;
import controller.ContatoController;
import model.Compromisso;
import model.Contato;

public class JanelaAgenda extends javax.swing.JDialog {

	private static final long serialVersionUID = 1L;
	
	private ContatoController ctrlContato = new ContatoController();
	private CompromissoController ctrlComp = new CompromissoController();
	private final Contato contato;
	
	public JanelaAgenda(int idContato, JFrame parent, boolean modal) throws Exception {
        super(parent, modal);
        contato = ctrlContato.obterPeloId(idContato);
        initComponents();
    }

	private ListModel<Compromisso> getListModel(){
		return new AbstractListModel<Compromisso>() {
			private static final long serialVersionUID = 1L;
			List<Compromisso> listaInterna = ctrlComp.obterCompromissos(contato.getId());
            public int getSize() { return listaInterna.size(); }
            public Compromisso getElementAt(int i) { return listaInterna.get(i); }
		};
	}
	
	private void initEventos() {
        btnExcluir.addActionListener((ActionEvent e) -> {
        	if(lstCompromissos.getSelectedIndex()>-1) {
        		ctrlComp.excluirCompromisso(
        				lstCompromissos.getModel().getElementAt(
        					lstCompromissos.getSelectedIndex()).getId()
        				);
        		lstCompromissos.setModel(getListModel());
        	}
        });
        btnIncluir.addActionListener((ActionEvent e) -> {
        	try {
        		Compromisso c2 = new Compromisso();
        		c2.setIdContato(contato);
        		c2.setDuracao(Integer.parseInt(edtDuracao.getText()));
        		GregorianCalendar gcal = new GregorianCalendar();
        		String[] dmy = edtDia.getText().split("/");
        		String[] hm = edtHora.getText().split(":");
        		gcal.set(GregorianCalendar.DAY_OF_MONTH, Integer.parseInt(dmy[0]));
        		gcal.set(GregorianCalendar.MONTH, Integer.parseInt(dmy[1]) -1 );
        		gcal.set(GregorianCalendar.YEAR, Integer.parseInt(dmy[2]));
        		c2.setDia(gcal.getTime());
        		gcal.setTimeInMillis(0);
        		gcal.set(GregorianCalendar.HOUR_OF_DAY, Integer.parseInt(hm[0]));
        		gcal.set(GregorianCalendar.MINUTE, Integer.parseInt(hm[1]));
        		c2.setHora(gcal.getTime());
        		ctrlComp.inserirCompromisso(c2);
        		lstCompromissos.setModel(getListModel());
        	}catch(Exception ex) {
        		
        	}
        });
	}
	
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        lstCompromissos = new javax.swing.JList<>();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        edtDia = new javax.swing.JFormattedTextField();
        edtHora = new javax.swing.JFormattedTextField();
        edtDuracao = new javax.swing.JTextField();
        btnExcluir = new javax.swing.JButton();
        btnIncluir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lstCompromissos.setModel(getListModel());
        jScrollPane1.setViewportView(lstCompromissos);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Dia");

        jLabel2.setText("Hora");

        jLabel3.setText("Duração");

        edtDia.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getDateInstance(java.text.DateFormat.SHORT))));
        edtDia.setToolTipText("Digite no formato DD/MM/YYYY");

        edtHora.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getTimeInstance(java.text.DateFormat.SHORT))));
        edtHora.setToolTipText("Digite no formato HH:MM");

        edtDuracao.setToolTipText("");

        btnExcluir.setBackground(new java.awt.Color(204, 51, 0));
        btnExcluir.setText("EXCLUIR");

        btnIncluir.setText("INCLUIR");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(26, 26, 26)
                        .addComponent(edtDia))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(edtHora, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(edtDuracao, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 120, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnExcluir, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnIncluir, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(45, 45, 45))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(edtDia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(edtHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(edtDuracao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(btnExcluir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnIncluir)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        initEventos();
        pack();
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JanelaAgenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JanelaAgenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JanelaAgenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JanelaAgenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JanelaAgenda dialog;
				try {
					dialog = new JanelaAgenda(1, new javax.swing.JFrame(), true);
	                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
	                    @Override
	                    public void windowClosing(java.awt.event.WindowEvent e) {
	                        System.exit(0);
	                    }
	                });
	                dialog.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnIncluir;
    private javax.swing.JFormattedTextField edtDia;
    private javax.swing.JTextField edtDuracao;
    private javax.swing.JFormattedTextField edtHora;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<Compromisso> lstCompromissos;
    // End of variables declaration                   
}
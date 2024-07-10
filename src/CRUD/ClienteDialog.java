/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package CRUD;
import BaseDeDados.BancoDeDados;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Component;

public class ClienteDialog extends javax.swing.JDialog {

    public ClienteDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setResizable(false);
        setLocationRelativeTo(null);
    }
  
    
    BancoDeDados bd = new BancoDeDados();
    
    private void cadastroCliente(){
        if(bd.getConnection()){
            try {
                String query = "insert cliente(nome_cli,cpf_cli,dt_nasc_cli,"
                        + "endereco_cli, cidade_cli, sexo_cli)" 
                        + "values(?,?,?,?,?,?)";
                PreparedStatement smtp = bd.conexao.prepareStatement(query);
                smtp.setString(1, jTNome.getText());
                smtp.setString(2, jTCpf.getText());
                smtp.setString(3, jTDataNascimento.getText());
                smtp.setString(4, jTEndereco.getText());
                smtp.setString(5, jTCidade.getText());

                String valorSelecionado=jCSexo.getSelectedItem().toString();
                smtp.setString(6, valorSelecionado);

                smtp.executeUpdate();
                JOptionPane.showMessageDialog(null, "dados gravados");
                smtp.close();
                bd.conexao.close();
            } catch(SQLException erro){
                JOptionPane.showMessageDialog(null, "erro de gravação no Banco:"
                + erro.toString());
            }
        }
    }

    private void pesquisaCliente(){
        if(bd.getConnection()){
            try{
                String query = "select * from cliente where nome_cli like ?";
                PreparedStatement smtp = bd.conexao.prepareStatement(query);
                smtp.setString(1,"%"+jTPesquisa.getText()+"%");
                ResultSet rs = smtp.executeQuery();
                DefaultTableModel tabela = (DefaultTableModel) jTabelaCliente.getModel();
                tabela.setNumRows(0);
                while(rs.next()){
                    tabela.addRow(new Object[]{
                        rs.getString("id_cliente"),
                        rs.getString("nome_cli"),
                        rs.getString("cpf_cli"),
                        rs.getString("dt_nasc_cli"),
                        rs.getString("endereco_cli"),
                        rs.getString("cidade_cli"),
                        rs.getString("sexo_cli"),
                    });
                }
                smtp.close();
                bd.conexao.close();
            } catch(SQLException erro) {
                System.out.println("Erro ao pesquisar: "+erro.toString());
            }
        }
    }
    
    private void initTableListener(){
        jTabelaCliente.addMouseListener(new java.awt.event.MouseAdapter(){
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt){
                int selectedRow = jTabelaCliente.getSelectedRow();
                if(selectedRow != -1){
                    jLId.setText(jTabelaCliente.getValueAt(selectedRow, 0).toString());
                    jTNome.setText(jTabelaCliente.getValueAt(selectedRow, 1).toString());
                    jTCpf.setText(jTabelaCliente.getValueAt(selectedRow, 2).toString());
                    jTDataNascimento.setText(jTabelaCliente.getValueAt(selectedRow, 3).toString());
                    jTEndereco.setText(jTabelaCliente.getValueAt(selectedRow, 4).toString());
                    jTCidade.setText(jTabelaCliente.getValueAt(selectedRow, 5).toString());
                    jCSexo.setSelectedItem(jTabelaCliente.getValueAt(selectedRow, 6).toString());  
                }
            }
        });
    }
    
    private void editaDadosCliente(){
        if(bd.getConnection()){
            try{
                String query = "update cliente set nome_cli=?, cpf_cli=?, dt_nasc_cli=?, "+
                                "endereco_cli=?, cidade_cli=?, sexo_cli=? where id_cliente = ? ";
                PreparedStatement smtp = bd.conexao.prepareStatement(query);
                smtp.setString(1, jTNome.getText());
                smtp.setString(2, jTCpf.getText());
                smtp.setString(3, jTDataNascimento.getText());
                smtp.setString(4, jTEndereco.getText());
                smtp.setString(5, jTCidade.getText());
                smtp.setString(6, jCSexo.getSelectedItem().toString());
                smtp.setString(7, jLId.getText());
                smtp.executeUpdate();
                JOptionPane.showMessageDialog(null, "Dados atualizados! ");
                smtp.close();
                bd.conexao.close();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Erro no SQL: "+e.getMessage());
            }
        }
    }
    
    private void deletaCliente(){
        if(bd.getConnection()){
            try{
                String query = "delete from cliente where id_cliente = ?";
                PreparedStatement smtp = bd.conexao.prepareStatement(query);
                String index = (String)jTabelaCliente.getModel().getValueAt(jTabelaCliente.getSelectedRow(), 0);
                smtp.setString(1, index);
                int opcao = JOptionPane.showConfirmDialog(null, "Deseja excluir o cliente? ", "Confirmação ", JOptionPane.YES_NO_OPTION);
                if(opcao == JOptionPane.YES_OPTION){
                    int resultado = smtp.executeUpdate();
                    if(resultado>0){
                        JOptionPane.showMessageDialog(null, "Cliente deletado com sucesso! ");
                    }else{
                        JOptionPane.showMessageDialog(null, "Não foi possível remover o cliente! ");
                    }
                    smtp.close();
                    bd.conexao.close();
                }
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Erro no SQL: "+e.toString());
            }
        }
    }
    
    private static void limparCampos(JPanel jPanel){
        Component [] componentes = jPanel.getComponents();
        for(Component componente:componentes){
            if(componente instanceof JTextField){
                JTextField camposTF = (JTextField) componente;
                camposTF.setText("");
            }
            else if(componente instanceof JLabel){
                JLabel camposLabel = (JLabel) componente;
                camposLabel.setText("");
            }
        }
    }
    
    private void limparTabela(){
        DefaultTableModel table = (DefaultTableModel)jTabelaCliente.getModel();
        table.setNumRows(0);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField6 = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        BotaoSalvar = new javax.swing.JButton();
        BotaoExcluir = new javax.swing.JButton();
        BotaoLimpar = new javax.swing.JButton();
        BotaoEditar = new javax.swing.JButton();
        BotaoCancelar = new javax.swing.JButton();
        jDesktopPane2 = new javax.swing.JDesktopPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTabelaCliente = new javax.swing.JTable();
        jDesktopPane3 = new javax.swing.JDesktopPane();
        jPInformacoes = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPDadosPessoais = new javax.swing.JPanel();
        jCSexo = new javax.swing.JComboBox<>();
        jTDataNascimento = new javax.swing.JTextField();
        jTCidade = new javax.swing.JTextField();
        jTEndereco = new javax.swing.JTextField();
        jTCpf = new javax.swing.JTextField();
        jTNome = new javax.swing.JTextField();
        jPId = new javax.swing.JPanel();
        jLId = new javax.swing.JLabel();
        jDesktopPane4 = new javax.swing.JDesktopPane();
        jPanel5 = new javax.swing.JPanel();
        jTPesquisa = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();

        jTextField6.setText("jTextField6");

        jButton6.setText("jButton6");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(0, 40, 78));

        jLabel1.setFont(new java.awt.Font("Bosch Sans", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Cadastro de clientes");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(281, 281, 281)
                .addComponent(jLabel1)
                .addContainerGap(285, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 690, -1));

        jDesktopPane1.setBackground(new java.awt.Color(0, 40, 78));

        BotaoSalvar.setFont(new java.awt.Font("Bosch Sans", 0, 12)); // NOI18N
        BotaoSalvar.setText("Salvar");
        BotaoSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoSalvarActionPerformed(evt);
            }
        });

        BotaoExcluir.setFont(new java.awt.Font("Bosch Sans", 0, 12)); // NOI18N
        BotaoExcluir.setText("Excluir");
        BotaoExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoExcluirActionPerformed(evt);
            }
        });

        BotaoLimpar.setFont(new java.awt.Font("Bosch Sans", 0, 12)); // NOI18N
        BotaoLimpar.setText("Limpar");
        BotaoLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoLimparActionPerformed(evt);
            }
        });

        BotaoEditar.setFont(new java.awt.Font("Bosch Sans", 0, 12)); // NOI18N
        BotaoEditar.setText("Editar");
        BotaoEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoEditarActionPerformed(evt);
            }
        });

        BotaoCancelar.setFont(new java.awt.Font("Bosch Sans", 0, 12)); // NOI18N
        BotaoCancelar.setText("Cancelar");

        jDesktopPane1.setLayer(BotaoSalvar, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(BotaoExcluir, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(BotaoLimpar, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(BotaoEditar, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(BotaoCancelar, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(BotaoSalvar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 167, Short.MAX_VALUE)
                .addComponent(BotaoEditar)
                .addGap(35, 35, 35)
                .addComponent(BotaoExcluir)
                .addGap(33, 33, 33)
                .addComponent(BotaoLimpar)
                .addGap(32, 32, 32)
                .addComponent(BotaoCancelar)
                .addGap(31, 31, 31))
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BotaoSalvar)
                    .addComponent(BotaoEditar)
                    .addComponent(BotaoLimpar)
                    .addComponent(BotaoCancelar)
                    .addComponent(BotaoExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jDesktopPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 605, 690, -1));

        jDesktopPane2.setBackground(new java.awt.Color(0, 40, 78));
        jDesktopPane2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jDesktopPane2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabelaCliente.setFont(new java.awt.Font("Bosch Sans", 0, 12)); // NOI18N
        jTabelaCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Nome", "CPF", "Dt.nasc", "Endereço", "Cidade", "Sexo"
            }
        ));
        jScrollPane1.setViewportView(jTabelaCliente);
        if (jTabelaCliente.getColumnModel().getColumnCount() > 0) {
            jTabelaCliente.getColumnModel().getColumn(0).setHeaderValue("Nome");
            jTabelaCliente.getColumnModel().getColumn(1).setHeaderValue("CPF");
            jTabelaCliente.getColumnModel().getColumn(2).setHeaderValue("Dt.nasc");
            jTabelaCliente.getColumnModel().getColumn(3).setHeaderValue("Endereço");
            jTabelaCliente.getColumnModel().getColumn(4).setHeaderValue("Cidade");
            jTabelaCliente.getColumnModel().getColumn(5).setHeaderValue("Sexo");
        }

        jDesktopPane2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 8, 624, 190));

        jPanel1.add(jDesktopPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, 640, 210));

        jDesktopPane3.setBackground(new java.awt.Color(0, 40, 78));
        jDesktopPane3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPInformacoes.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Bosch Sans", 0, 12)); // NOI18N
        jLabel2.setText("Nome:");

        jLabel3.setFont(new java.awt.Font("Bosch Sans", 0, 12)); // NOI18N
        jLabel3.setText("Endereço:");

        jLabel4.setFont(new java.awt.Font("Bosch Sans", 0, 12)); // NOI18N
        jLabel4.setText("Cidade:");

        jLabel5.setFont(new java.awt.Font("Bosch Sans", 0, 12)); // NOI18N
        jLabel5.setText("Data de Nascimento:");

        jLabel6.setFont(new java.awt.Font("Bosch Sans", 0, 12)); // NOI18N
        jLabel6.setText("CPF:");

        jLabel7.setFont(new java.awt.Font("Bosch Sans", 0, 12)); // NOI18N
        jLabel7.setText("Sexo:");

        javax.swing.GroupLayout jPInformacoesLayout = new javax.swing.GroupLayout(jPInformacoes);
        jPInformacoes.setLayout(jPInformacoesLayout);
        jPInformacoesLayout.setHorizontalGroup(
            jPInformacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPInformacoesLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPInformacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6))
                .addContainerGap(136, Short.MAX_VALUE))
        );
        jPInformacoesLayout.setVerticalGroup(
            jPInformacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPInformacoesLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addGap(21, 21, 21))
        );

        jDesktopPane3.add(jPInformacoes, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 16, 300, 200));

        jPDadosPessoais.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPDadosPessoais.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jCSexo.setFont(new java.awt.Font("Bosch Sans", 0, 12)); // NOI18N
        jCSexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Masculino", "Feminino", "Não declarado" }));
        jCSexo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCSexoActionPerformed(evt);
            }
        });
        jPDadosPessoais.add(jCSexo, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 160, -1, -1));

        jTDataNascimento.setFont(new java.awt.Font("Bosch Sans", 0, 12)); // NOI18N
        jPDadosPessoais.add(jTDataNascimento, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 130, 121, -1));

        jTCidade.setFont(new java.awt.Font("Bosch Sans", 0, 12)); // NOI18N
        jPDadosPessoais.add(jTCidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 100, 121, -1));

        jTEndereco.setFont(new java.awt.Font("Bosch Sans", 0, 12)); // NOI18N
        jPDadosPessoais.add(jTEndereco, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 70, 121, -1));

        jTCpf.setFont(new java.awt.Font("Bosch Sans", 0, 12)); // NOI18N
        jPDadosPessoais.add(jTCpf, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 40, 121, -1));

        jTNome.setFont(new java.awt.Font("Bosch Sans", 0, 12)); // NOI18N
        jPDadosPessoais.add(jTNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 121, -1));

        jLId.setText("ID");

        javax.swing.GroupLayout jPIdLayout = new javax.swing.GroupLayout(jPId);
        jPId.setLayout(jPIdLayout);
        jPIdLayout.setHorizontalGroup(
            jPIdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPIdLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLId)
                .addContainerGap())
        );
        jPIdLayout.setVerticalGroup(
            jPIdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPIdLayout.createSequentialGroup()
                .addComponent(jLId)
                .addGap(0, 4, Short.MAX_VALUE))
        );

        jPDadosPessoais.add(jPId, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, 20, 20));

        jDesktopPane3.add(jPDadosPessoais, new org.netbeans.lib.awtextra.AbsoluteConstraints(338, 16, 280, 200));

        jPanel1.add(jDesktopPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 640, 230));

        jDesktopPane4.setBackground(new java.awt.Color(0, 40, 78));
        jDesktopPane4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTPesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTPesquisaActionPerformed(evt);
            }
        });
        jPanel5.add(jTPesquisa, new org.netbeans.lib.awtextra.AbsoluteConstraints(64, 8, 440, -1));

        jLabel8.setFont(new java.awt.Font("Bosch Sans", 0, 12)); // NOI18N
        jLabel8.setText("Nome:");
        jPanel5.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 11, -1, -1));

        jButton7.setFont(new java.awt.Font("Bosch Sans", 0, 12)); // NOI18N
        jButton7.setText("Pesquisar");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(516, 8, 90, -1));

        jDesktopPane4.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 620, 40));

        jPanel1.add(jDesktopPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 640, 60));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 641, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BotaoSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoSalvarActionPerformed
        cadastroCliente();
        pesquisaCliente();
        limparCampos(jPDadosPessoais);
        limparCampos(jPId);
    }//GEN-LAST:event_BotaoSalvarActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        pesquisaCliente();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jTPesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTPesquisaActionPerformed
        pesquisaCliente();
    }//GEN-LAST:event_jTPesquisaActionPerformed

    private void jCSexoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCSexoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCSexoActionPerformed

    private void BotaoEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoEditarActionPerformed
        initTableListener();
        editaDadosCliente();
        pesquisaCliente();
        limparCampos(jPDadosPessoais);
        limparCampos(jPId);
    }//GEN-LAST:event_BotaoEditarActionPerformed

    private void BotaoExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoExcluirActionPerformed
        deletaCliente();
        limparCampos(jPDadosPessoais);
        limparCampos(jPId);
        limparTabela();
        pesquisaCliente();
    }//GEN-LAST:event_BotaoExcluirActionPerformed

    private void BotaoLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoLimparActionPerformed
        limparCampos(jPDadosPessoais);
        limparCampos(jPId);
        limparTabela();
    }//GEN-LAST:event_BotaoLimparActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ClienteDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClienteDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClienteDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClienteDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ClienteDialog dialog = new ClienteDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotaoCancelar;
    private javax.swing.JButton BotaoEditar;
    private javax.swing.JButton BotaoExcluir;
    private javax.swing.JButton BotaoLimpar;
    private javax.swing.JButton BotaoSalvar;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JComboBox<String> jCSexo;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JDesktopPane jDesktopPane2;
    private javax.swing.JDesktopPane jDesktopPane3;
    private javax.swing.JDesktopPane jDesktopPane4;
    private javax.swing.JLabel jLId;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPDadosPessoais;
    private javax.swing.JPanel jPId;
    private javax.swing.JPanel jPInformacoes;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTCidade;
    private javax.swing.JTextField jTCpf;
    private javax.swing.JTextField jTDataNascimento;
    private javax.swing.JTextField jTEndereco;
    private javax.swing.JTextField jTNome;
    private javax.swing.JTextField jTPesquisa;
    private javax.swing.JTable jTabelaCliente;
    private javax.swing.JTextField jTextField6;
    // End of variables declaration//GEN-END:variables
}

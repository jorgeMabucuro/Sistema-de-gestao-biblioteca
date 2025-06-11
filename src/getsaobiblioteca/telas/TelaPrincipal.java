/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package getsaobiblioteca.telas;

import getsaobiblioteca.conexao.ModuloConexao;
import getsaobiblioteca.entidade.CriarTexto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Jorge Mabucuro
 */
public class TelaPrincipal extends javax.swing.JFrame {

    Connection conexao = null;
    PreparedStatement pre = null;
    ResultSet rs = null;
    private String status;

//   Usuario 
     private void alterar() {
        String sql = "update usuario set nome=?, endereco=?, telefone=?, email=?,senha=? where nome=?";
        try {
            pre = conexao.prepareStatement(sql);
            pre.setString(1, nome.getText());
            pre.setString(2, endereco.getText());
            pre.setString(3, telefone.getText());
            pre.setString(4, email.getText());
            String capt = new String(senha.getPassword());
            pre.setString(5, capt);
            pre.setString(6, jTextPesquisar.getText());

            if (JOptionPane.showConfirmDialog(this, "Deseja alterar o usuario?", 
                    "Alterar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                pre.executeUpdate();
                limparCampos();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void adicionar() {
        String sql = "insert into usuario(nome, endereco, telefone, email,senha) Values (?,?,?,?,?)";
        try {
            pre = conexao.prepareStatement(sql);
            pre.setString(1, nome.getText());
            pre.setString(2, endereco.getText());
            pre.setString(3, telefone.getText());
            pre.setString(4, email.getText());
            String capt = new String(senha.getPassword());
            pre.setString(5, capt);
            pre.executeUpdate();
            JOptionPane.showMessageDialog(null, " Cadrastrado com sucesso");
            limparCampos();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void buscar() {
        String sql = "Select * from usuario where nome = ?";
        try {
            pre = conexao.prepareStatement(sql);
            pre.setString(1, jTextPesquisar.getText());
            rs = pre.executeQuery();

            if (rs.next()) {
                nome.setText(rs.getString(2));
                endereco.setText(rs.getString(3));
                telefone.setText(rs.getString(4));
                email.setText(rs.getString(5));
                senha.setText(rs.getString(6));

            } else {
                JOptionPane.showMessageDialog(null, " Usuario não encontrado");
                limparCampos();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }


    private void apagar() {
        String sql = "delete from usuario where nome=?";
        try {
            pre = conexao.prepareStatement(sql);
            pre.setString(1, jTextPesquisar.getText());

            if (JOptionPane.showConfirmDialog(this, "Deseja remover o usuario?", "Apagar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                pre.executeUpdate();
                limparCampos();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void limparCampos() {
        nome.setText("");
        endereco.setText("");
        telefone.setText("");
        email.setText("");
        senha.setText("");
    }

    // livros 
    public void pesquisarLivros() {
        String sql = "select * from livros where titulo like ? "; //
        try {
            pre = conexao.prepareStatement(sql);
            pre.setString(1, txtPesquisar.getText() + "%");
            rs = pre.executeQuery();
            tbLivros.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void pesquisar() {
        String sql = "Select * from livros where titulo = ?";
        try {
            pre = conexao.prepareStatement(sql);
            pre.setString(1, livropesquisa.getText());
            rs = pre.executeQuery();

            if (rs.next()) {
                txtTitulo.setText(rs.getString(2));
                txtAutor.setText(rs.getString(3));
                txtCategoria.setText(rs.getString(4));
                txtanoPublicacao.setText(rs.getString(5));
                txtEditora.setText(rs.getString(6));
                txtQuantidade.setText(rs.getString(7));
            } else {
                JOptionPane.showMessageDialog(null, " Livro não encontrado");
                limpar();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void salvar() {
        String sql = "insert into livros(titulo, autor, categoria, anoPublicacao,editora,quantidade) Values (?,?,?,?,?,?)";
        try {
            pre = conexao.prepareStatement(sql);
            pre.setString(1, txtTitulo.getText());
            pre.setString(2, txtAutor.getText());
            pre.setString(3, txtCategoria.getText());
            pre.setString(4, txtanoPublicacao.getText());
            pre.setString(5, txtEditora.getText());
            pre.setString(6, txtQuantidade.getText());
            pre.executeUpdate();
            JOptionPane.showMessageDialog(null, " Livro salvo com sucesso");
            limpar();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void update() {
        String sql = "update livros set titulo=?, autor=?, categoria=?, anoPublicacao=?,editora=?,quantidade=? where titulo=?";
        try {
            pre = conexao.prepareStatement(sql);
            pre.setString(1, txtTitulo.getText());
            pre.setString(2, txtAutor.getText());
            pre.setString(3, txtCategoria.getText());
            pre.setString(4, txtanoPublicacao.getText());
            pre.setString(5, txtEditora.getText());
            pre.setString(6, txtQuantidade.getText());
            pre.setString(7, livropesquisa.getText());

            if (JOptionPane.showConfirmDialog(this, "Deseja alterar esse livro?", "Alterar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                pre.executeUpdate();
                limpar();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void delete() {
        String sql = "delete from livros where titulo=?";
        try {
            pre = conexao.prepareStatement(sql);
            pre.setString(1, livropesquisa.getText());

            if (JOptionPane.showConfirmDialog(this, "Deseja remover esse livro?", "Apagar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                pre.executeUpdate();
                limpar();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void limpar() {
        txtTitulo.setText("");
        txtAutor.setText("");
        txtCategoria.setText("");
        txtanoPublicacao.setText("");
        txtEditora.setText("");
        txtQuantidade.setText("");
    }

    //emprestimo
    private void pesquisarEmprestimoLivro() {
        String sql = "select * from livros where titulo like ?";
        try {
            pre = conexao.prepareStatement(sql);
            pre.setString(1, TxtpesquisarEmpLivro.getText() + "%");
            rs = pre.executeQuery();
            tbLivrosEmp.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void setarCampoLivro() {
        int setar = tbLivrosEmp.getSelectedRow();
        txtEmprestimoTitulo.setText(tbLivrosEmp.getModel().getValueAt(setar, 1).toString());
        id_livro.setVisible(false);
        id_livro.setText(tbLivrosEmp.getModel().getValueAt(setar, 0).toString());
    }

    private void pesquisarEmprestimoUsuario() {
        String sql = "select * from usuario where nome like ?";
        try {
            pre = conexao.prepareStatement(sql);
            pre.setString(1, txtPesquisarEmpUsuario.getText() + "%");
            rs = pre.executeQuery();
            tbUsuarioEmp.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void setarCampoUsuario() {
        int setar = tbUsuarioEmp.getSelectedRow();
        txtUsuarioEmpt.setText(tbUsuarioEmp.getModel().getValueAt(setar, 1).toString());
        id_usuario.setVisible(false);
        id_usuario.setText(tbUsuarioEmp.getModel().getValueAt(setar, 0).toString());
    }

    private void AdicionarEmprestimo() {
        String sql = "insert into emprestimo(Titulo, usario, id_livro, id_usuario,data_emprestimo,data_entrega,status) Values (?,?,?,?,?,?,?)";
        try {
            pre = conexao.prepareStatement(sql);
            pre.setString(1, txtEmprestimoTitulo.getText());
            pre.setString(2, txtUsuarioEmpt.getText());
            pre.setString(3, id_livro.getText());
            pre.setString(4, id_usuario.getText());
            pre.setString(5, txtDataEmprestimo.getText());
            pre.setString(6, txtDataEntrega.getText());
            pre.setString(7, status);
            pre.executeUpdate();

            if (status.equals("Empretado")) {
                JOptionPane.showMessageDialog(null, " Emprestimo feito com sucesso");
            } else {
                JOptionPane.showMessageDialog(null, " Devolução feita com sucesso");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void emprestarLivro() {
        String sql = "update livros set quantidade = quantidade -1 where titulo=?";
        try {
            pre = conexao.prepareStatement(sql);
            pre.setString(1, txtEmprestimoTitulo.getText());
            pre.executeUpdate();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void devoverLivro() {
        String sql = "update livros set quantidade = quantidade + 1 where titulo=?";
        try {
            pre = conexao.prepareStatement(sql);
            pre.setString(1, txtEmprestimoTitulo.getText());
            pre.executeUpdate();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void limparCamposEmprestimo() {
        TxtpesquisarEmpLivro.setText("");
        txtPesquisarEmpUsuario.setText("");
        txtEmprestimoTitulo.setText("");
        txtUsuarioEmpt.setText("");

    }
//    public void data(){
//    LocalDate dataEmprestimo = LocalDate.now();
//        DateTimeFormatter dataFormatada = DateTimeFormatter.ofPattern("dd-mm-yyyy");
//        String dataActual = dataEmprestimo.format(dataFormatada);
//        txtDataEmprestimo.setText(dataActual);
//        
//        LocalDate dataDevolver = LocalDate.now().plusDays(7);
//        DateTimeFormatter dataFormatar = DateTimeFormatter.ofPattern("dd-mm-yyyy");
//        String dataDevover = dataEmprestimo.format(dataFormatar);
//        txtDataEntrega.setText(dataDevover);
//    } 
    
    



//relatorio
    public void relatorio() {
        String sql = "select * from emprestimo ";
        try {
            pre = conexao.prepareStatement(sql);
            rs = pre.executeQuery();
            tbRelatorioEmprestimo.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void relatorioUsuario() {
        String sql = "select * from usuario ";
        try {
            pre = conexao.prepareStatement(sql);
            rs = pre.executeQuery();
            tbRelatorioEmprestimo.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    



// recibo
    private void relatorioEmprestimo() {
        CriarTexto file = new CriarTexto();
        file.abreEscrita("C:\\Users\\Jorge Mabucuro\\Desktop\\Relatorio\\" + txtUsuarioEmpt.getText() + ".txt");
        file.escreverLinha("_______________________________________________________________");
        file.escreverLinha("                            The Legend Bokks");
        file.escreverLinha("_______________________________________________________________");
        file.escreverLinha("|    Nome: " + txtUsuarioEmpt.getText() + "                     |");
        file.escreverLinha("|    Titulo: " + txtEmprestimoTitulo.getText() + "              |");
        file.escreverLinha("|    Serviço: " + status + "                                    |");
        file.escreverLinha("|    Data de emprestimo: " + txtDataEmprestimo.getText() + "    |");
        file.escreverLinha("|    Data de devolução: " + txtDataEntrega.getText() + "        |");
        file.escreverLinha("|                                                             |");
        file.escreverLinha("________________________________________________________________");
        file.fechaEscrita();

    }

    /**
     * Creates new form telaPrincipal
     */
    public TelaPrincipal() {
        initComponents();
        conexao = ModuloConexao.conectar();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane5 = new javax.swing.JTabbedPane();
        buttonGroup1 = new javax.swing.ButtonGroup();
        id_usuario = new javax.swing.JTextField();
        id_livro = new javax.swing.JTextField();
        jTabbedUsuarios = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        txtPesquisar = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbLivros = new javax.swing.JTable();
        jLabel16 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txtTitulo = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtAutor = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtCategoria = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtanoPublicacao = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtEditora = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtQuantidade = new javax.swing.JTextField();
        btSalvar = new javax.swing.JButton();
        btUpdate = new javax.swing.JButton();
        btDelete = new javax.swing.JButton();
        livropesquisa = new javax.swing.JTextField();
        btlivropesquisa = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        telefone = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        email = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        senha = new javax.swing.JPasswordField();
        nome = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        endereco = new javax.swing.JTextField();
        jTextPesquisar = new javax.swing.JTextField();
        pesquisa = new javax.swing.JButton();
        jBAlterar = new javax.swing.JButton();
        jBApagar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        TxtpesquisarEmpLivro = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbUsuarioEmp = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbLivrosEmp = new javax.swing.JTable();
        txtPesquisarEmpUsuario = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtEmprestimoTitulo = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        btAdicionarEmprestimo = new javax.swing.JButton();
        rbEmprestimo = new javax.swing.JRadioButton();
        rbDevolver = new javax.swing.JRadioButton();
        btimprimirEmp = new javax.swing.JButton();
        txtUsuarioEmpt = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtDataEmprestimo = new javax.swing.JTextField();
        txtDataEntrega = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbRelatorioEmprestimo = new javax.swing.JTable();
        btRelatorioEmp = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gestão da Biblioteca");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jTabbedUsuarios.setBackground(new java.awt.Color(0, 0, 0));

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));

        txtPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPesquisarActionPerformed(evt);
            }
        });
        txtPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisarKeyReleased(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel4.setText("Livros");

        tbLivros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Titulo", "Autor", "Categoria", "Ano", "Editora"
            }
        ));
        jScrollPane1.setViewportView(tbLivros);

        jLabel16.setText("Pesquise os livros ");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(txtPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 545, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(41, 41, 41)
                        .addComponent(jLabel16)))
                .addGap(57, 57, 57)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(136, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Visualizar livros", jPanel5);

        jLabel10.setText("Titulo");

        jLabel11.setText("Autor");

        jLabel12.setText("Categoria");

        jLabel13.setText("Ano de Publicação");

        jLabel14.setText("Editora");

        jLabel15.setText("Quantidade");

        btSalvar.setText("Salvar");
        btSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSalvarActionPerformed(evt);
            }
        });

        btUpdate.setText("Actualizar");
        btUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btUpdateActionPerformed(evt);
            }
        });

        btDelete.setText("Apagar");
        btDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDeleteActionPerformed(evt);
            }
        });

        btlivropesquisa.setText("Pesquisa");
        btlivropesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btlivropesquisaActionPerformed(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel24.setText("Cadastro de livros");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addComponent(btSalvar)
                        .addGap(35, 35, 35)
                        .addComponent(btUpdate)
                        .addGap(27, 27, 27)
                        .addComponent(btDelete))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel6Layout.createSequentialGroup()
                                    .addComponent(jLabel12)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(jLabel14)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtEditora, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(jLabel15)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(jLabel13)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtanoPublicacao, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel6Layout.createSequentialGroup()
                                    .addComponent(jLabel11)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtAutor, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(livropesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(31, 31, 31)
                                        .addComponent(btlivropesquisa))
                                    .addComponent(txtTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(194, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(livropesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btlivropesquisa))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(24, 24, 24)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtanoPublicacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtEditora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel15)
                    .addComponent(txtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btSalvar)
                    .addComponent(btUpdate)
                    .addComponent(btDelete))
                .addContainerGap(161, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Livros", jPanel6);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("");

        jTabbedUsuarios.addTab("Livros", jPanel1);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Cadratro do usuario");

        jLabel6.setText("Senha");

        jButton1.setText("Criar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel5.setText("Nome");

        jLabel7.setText("Endereço");

        jLabel8.setText("Telefone");

        jLabel9.setText("Email");

        pesquisa.setText("Pesquisar");
        pesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pesquisaActionPerformed(evt);
            }
        });

        jBAlterar.setText("Alterar");
        jBAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAlterarActionPerformed(evt);
            }
        });

        jBApagar.setText("Apagar");
        jBApagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBApagarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel3))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel5)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(nome)
                            .addComponent(endereco)
                            .addComponent(telefone)
                            .addComponent(email)
                            .addComponent(senha, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(98, 98, 98)
                        .addComponent(jTextPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pesquisa))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(108, 108, 108)
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jBAlterar)
                        .addGap(18, 18, 18)
                        .addComponent(jBApagar)))
                .addContainerGap(213, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pesquisa))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(endereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(telefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(senha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jBAlterar)
                    .addComponent(jBApagar))
                .addContainerGap(199, Short.MAX_VALUE))
        );

        jTabbedUsuarios.addTab("Usuarios", jPanel2);

        jPanel3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jPanel3FocusGained(evt);
            }
        });
        jPanel3.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                jPanel3InputMethodTextChanged(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Emprestimo");

        TxtpesquisarEmpLivro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TxtpesquisarEmpLivroKeyReleased(evt);
            }
        });

        tbUsuarioEmp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbUsuarioEmp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbUsuarioEmpMouseClicked(evt);
            }
        });
        tbUsuarioEmp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbUsuarioEmpKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tbUsuarioEmp);

        tbLivrosEmp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbLivrosEmp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbLivrosEmpMouseClicked(evt);
            }
        });
        tbLivrosEmp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbLivrosEmpKeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(tbLivrosEmp);

        txtPesquisarEmpUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisarEmpUsuarioKeyReleased(evt);
            }
        });

        jLabel17.setText("Titulo");
        jLabel17.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jLabel17PropertyChange(evt);
            }
        });

        jLabel18.setText("Usuario");

        btAdicionarEmprestimo.setText("Adicionar");
        btAdicionarEmprestimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAdicionarEmprestimoActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbEmprestimo);
        rbEmprestimo.setText("Emprestimo");
        rbEmprestimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbEmprestimoActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbDevolver);
        rbDevolver.setText("Devolver");
        rbDevolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbDevolverActionPerformed(evt);
            }
        });

        btimprimirEmp.setText("Imprimir");
        btimprimirEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btimprimirEmpActionPerformed(evt);
            }
        });

        jLabel19.setText("Pesquisar o livro");

        jLabel20.setText("Pesquisar o Usuario");

        txtDataEmprestimo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDataEmprestimoFocusGained(evt);
            }
        });
        txtDataEmprestimo.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtDataEmprestimoPropertyChange(evt);
            }
        });

        txtDataEntrega.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDataEntregaFocusGained(evt);
            }
        });

        jLabel21.setText("Data entrega");

        jLabel22.setText("Data emprestimo");

        jLabel23.setText("yyyy-mm-dd");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btAdicionarEmprestimo)
                .addGap(18, 18, 18)
                .addComponent(btimprimirEmp)
                .addGap(127, 127, 127))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(TxtpesquisarEmpLivro, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(jLabel19))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtPesquisarEmpUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel20))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtDataEmprestimo, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                                    .addComponent(txtDataEntrega)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel18))
                                .addGap(12, 12, 12)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtUsuarioEmpt, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtEmprestimoTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(66, 66, 66)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rbEmprestimo)
                                    .addComponent(rbDevolver)))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(131, 131, 131)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 556, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(TxtpesquisarEmpLivro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPesquisarEmpUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtEmprestimoTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rbEmprestimo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(rbDevolver)
                    .addComponent(txtUsuarioEmpt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(txtDataEmprestimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(txtDataEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btAdicionarEmprestimo)
                    .addComponent(btimprimirEmp))
                .addGap(21, 21, 21))
        );

        jTabbedUsuarios.addTab("Emprestimo", jPanel3);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Relatorios");

        tbRelatorioEmprestimo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(tbRelatorioEmprestimo);

        btRelatorioEmp.setText("Relatorio Emprestimo");
        btRelatorioEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRelatorioEmpActionPerformed(evt);
            }
        });

        jButton3.setText("Relatorio Usuario");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 514, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addComponent(btRelatorioEmp)
                        .addGap(60, 60, 60)
                        .addComponent(jButton3)))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(34, 34, 34)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btRelatorioEmp)
                    .addComponent(jButton3))
                .addGap(73, 73, 73))
        );

        jTabbedUsuarios.addTab("Relatorios", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedUsuarios, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedUsuarios, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        jTabbedUsuarios.getAccessibleContext().setAccessibleName("Biblioteca");

        setSize(new java.awt.Dimension(597, 597));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        adicionar();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void pesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pesquisaActionPerformed
        // TODO add your handling code here:
        buscar();
    }//GEN-LAST:event_pesquisaActionPerformed

    private void jBAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAlterarActionPerformed
        // TODO add your handling code here:
        alterar();
    }//GEN-LAST:event_jBAlterarActionPerformed

    private void jBApagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBApagarActionPerformed
        // TODO add your handling code here:
        apagar();
    }//GEN-LAST:event_jBApagarActionPerformed

    private void btDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDeleteActionPerformed
        // TODO add your handling code here:
        delete();
    }//GEN-LAST:event_btDeleteActionPerformed

    private void btSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalvarActionPerformed
        // TODO add your handling code here:
        salvar();
    }//GEN-LAST:event_btSalvarActionPerformed

    private void btUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btUpdateActionPerformed
        // TODO add your handling code here:
        update();
    }//GEN-LAST:event_btUpdateActionPerformed

    private void btlivropesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btlivropesquisaActionPerformed
        // TODO add your handling code here:
        pesquisar();
    }//GEN-LAST:event_btlivropesquisaActionPerformed

    private void txtPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisarKeyReleased
        // TODO add your handling code here:
        pesquisarLivros();
    }//GEN-LAST:event_txtPesquisarKeyReleased

    private void txtPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPesquisarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPesquisarActionPerformed

    private void TxtpesquisarEmpLivroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtpesquisarEmpLivroKeyReleased
        // TODO add your handling code here:
        pesquisarEmprestimoLivro();
    }//GEN-LAST:event_TxtpesquisarEmpLivroKeyReleased

    private void txtPesquisarEmpUsuarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisarEmpUsuarioKeyReleased
        // TODO add your handling code here:
        pesquisarEmprestimoUsuario();
    }//GEN-LAST:event_txtPesquisarEmpUsuarioKeyReleased

    private void tbLivrosEmpKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbLivrosEmpKeyReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_tbLivrosEmpKeyReleased

    private void tbUsuarioEmpKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbUsuarioEmpKeyReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_tbUsuarioEmpKeyReleased

    private void tbLivrosEmpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbLivrosEmpMouseClicked
        // TODO add your handling code here:
        setarCampoLivro();
    }//GEN-LAST:event_tbLivrosEmpMouseClicked

    private void tbUsuarioEmpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbUsuarioEmpMouseClicked
        // TODO add your handling code here:
        setarCampoUsuario();
    }//GEN-LAST:event_tbUsuarioEmpMouseClicked

    private void rbEmprestimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbEmprestimoActionPerformed
        // TODO add your handling code here:
        status = "Emprestado";
        emprestarLivro();
    }//GEN-LAST:event_rbEmprestimoActionPerformed

    private void rbDevolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbDevolverActionPerformed
        // TODO add your handling code here:
        status = "Devolvido";
        devoverLivro();
    }//GEN-LAST:event_rbDevolverActionPerformed

    private void btAdicionarEmprestimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAdicionarEmprestimoActionPerformed
        // TODO add your handling code here:
        AdicionarEmprestimo();
    }//GEN-LAST:event_btAdicionarEmprestimoActionPerformed

    private void btRelatorioEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRelatorioEmpActionPerformed
        // TODO add your handling code here:
        relatorio();
    }//GEN-LAST:event_btRelatorioEmpActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        relatorioUsuario();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
//        // TODO add your handling code here:
//        LocalDate dataEmprestimo = LocalDate.now();
//        DateTimeFormatter dataFormatada = DateTimeFormatter.ofPattern("dd-mm-yyyy");
//        String dataActual = dataEmprestimo.format(dataFormatada);
//        txtDataEmprestimo.setText(dataActual);
//        
//        LocalDate dataDevolver = LocalDate.now().plusDays(7);
//        DateTimeFormatter dataFormatar = DateTimeFormatter.ofPattern("dd-mm-yyyy");
//        String dataDevover = dataEmprestimo.format(dataFormatar);
//        txtDataEntrega.setText(dataDevover);
    }//GEN-LAST:event_formWindowActivated

    private void jLabel17PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jLabel17PropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel17PropertyChange

    private void txtDataEmprestimoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDataEmprestimoFocusGained
        // TODO add your handling code here:

    }//GEN-LAST:event_txtDataEmprestimoFocusGained

    private void txtDataEntregaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDataEntregaFocusGained
        // TODO add your handling code here:

    }//GEN-LAST:event_txtDataEntregaFocusGained

    private void jPanel3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPanel3FocusGained
        // TODO add your handling code here:

    }//GEN-LAST:event_jPanel3FocusGained

    private void btimprimirEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btimprimirEmpActionPerformed
        // TODO add your handling code here:
        relatorioEmprestimo();
        limparCamposEmprestimo();
    }//GEN-LAST:event_btimprimirEmpActionPerformed

    private void jPanel3InputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_jPanel3InputMethodTextChanged
        // TODO add your handling code here:

    }//GEN-LAST:event_jPanel3InputMethodTextChanged

    private void txtDataEmprestimoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtDataEmprestimoPropertyChange
        // TODO add your handling code here:

    }//GEN-LAST:event_txtDataEmprestimoPropertyChange

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
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField TxtpesquisarEmpLivro;
    private javax.swing.JButton btAdicionarEmprestimo;
    private javax.swing.JButton btDelete;
    private javax.swing.JButton btRelatorioEmp;
    private javax.swing.JButton btSalvar;
    private javax.swing.JButton btUpdate;
    private javax.swing.JButton btimprimirEmp;
    private javax.swing.JButton btlivropesquisa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTextField email;
    private javax.swing.JTextField endereco;
    private javax.swing.JTextField id_livro;
    private javax.swing.JTextField id_usuario;
    private javax.swing.JButton jBAlterar;
    private javax.swing.JButton jBApagar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane5;
    private javax.swing.JTabbedPane jTabbedUsuarios;
    private javax.swing.JTextField jTextPesquisar;
    private javax.swing.JTextField livropesquisa;
    private javax.swing.JTextField nome;
    private javax.swing.JButton pesquisa;
    private javax.swing.JRadioButton rbDevolver;
    private javax.swing.JRadioButton rbEmprestimo;
    private javax.swing.JPasswordField senha;
    private javax.swing.JTable tbLivros;
    private javax.swing.JTable tbLivrosEmp;
    private javax.swing.JTable tbRelatorioEmprestimo;
    private javax.swing.JTable tbUsuarioEmp;
    private javax.swing.JTextField telefone;
    private javax.swing.JTextField txtAutor;
    private javax.swing.JTextField txtCategoria;
    private javax.swing.JTextField txtDataEmprestimo;
    private javax.swing.JTextField txtDataEntrega;
    private javax.swing.JTextField txtEditora;
    private javax.swing.JTextField txtEmprestimoTitulo;
    private javax.swing.JTextField txtPesquisar;
    private javax.swing.JTextField txtPesquisarEmpUsuario;
    private javax.swing.JTextField txtQuantidade;
    private javax.swing.JTextField txtTitulo;
    private javax.swing.JTextField txtUsuarioEmpt;
    private javax.swing.JTextField txtanoPublicacao;
    // End of variables declaration//GEN-END:variables

}

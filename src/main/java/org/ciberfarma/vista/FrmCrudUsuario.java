package org.ciberfarma.vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.ciberfarma.modelo.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class FrmCrudUsuario extends JFrame {

	private JPanel contentPane;
	private JTextField txtCodigo;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtUsuario;
	private JTextField txtClave;
	private JTextField txtFechaNac;
	private JTextField txtTipo;
	private JTextField txtEstado;
	private 	JTextArea txtS;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmCrudUsuario frame = new FrmCrudUsuario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmCrudUsuario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 603, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("codigo");
		lblNewLabel.setBounds(10, 11, 46, 14);
		contentPane.add(lblNewLabel);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(58, 8, 86, 20);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		JLabel lblNombre = new JLabel("nombre");
		lblNombre.setBounds(10, 37, 46, 14);
		contentPane.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(58, 34, 86, 20);
		contentPane.add(txtNombre);
		
		JLabel lblApellido = new JLabel("apellido");
		lblApellido.setBounds(10, 65, 46, 14);
		contentPane.add(lblApellido);
		
		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(58, 62, 86, 20);
		contentPane.add(txtApellido);
		
		JLabel lblUsuario = new JLabel("usuario");
		lblUsuario.setBounds(10, 93, 46, 14);
		contentPane.add(lblUsuario);
		
		txtUsuario = new JTextField();
		txtUsuario.setColumns(10);
		txtUsuario.setBounds(58, 90, 86, 20);
		contentPane.add(txtUsuario);
		
		JLabel lblClave = new JLabel("clave");
		lblClave.setBounds(10, 121, 46, 14);
		contentPane.add(lblClave);
		
		txtClave = new JTextField();
		txtClave.setColumns(10);
		txtClave.setBounds(58, 118, 86, 20);
		contentPane.add(txtClave);
		
		JLabel lblFechaNac = new JLabel("fecha nac");
		lblFechaNac.setBounds(10, 149, 46, 14);
		contentPane.add(lblFechaNac);
		
		txtFechaNac = new JTextField();
		txtFechaNac.setColumns(10);
		txtFechaNac.setBounds(58, 146, 86, 20);
		contentPane.add(txtFechaNac);
		
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(10, 177, 46, 14);
		contentPane.add(lblTipo);
		
		txtTipo = new JTextField();
		txtTipo.setColumns(10);
		txtTipo.setBounds(58, 174, 86, 20);
		contentPane.add(txtTipo);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setBounds(10, 205, 46, 14);
		contentPane.add(lblEstado);
		
		txtEstado = new JTextField();
		txtEstado.setColumns(10);
		txtEstado.setBounds(58, 202, 86, 20);
		contentPane.add(txtEstado);
		
		txtS = new JTextArea();
		txtS.setBounds(10, 240, 543, 200);
		contentPane.add(txtS);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				registro();
			}

			
		});
		btnRegistrar.setBounds(305, 21, 89, 23);
		contentPane.add(btnRegistrar);
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				consultar();
			}
		});
		btnConsultar.setBounds(305, 56, 89, 23);
		contentPane.add(btnConsultar);
		
		JButton btnListado = new JButton("Listado");
		btnListado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			listado();
			}
			
			
			
		});
		btnListado.setBounds(305, 89, 89, 23);
		contentPane.add(btnListado);
		
	
		
	}
	 void listado() {
	
		 //Obtener un listado de los usuarios
		 
		 //muestro el listado en el txt/pdf
		 EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		 EntityManager em = fabrica.createEntityManager();
		 
		 //List<Usuario> lstUsuarios = em.createNamedQuery("Usuario.findAll",Usuario.class).getResultList();
		 
		 
		 List<Usuario> lstUsuarios;
		 if(txtTipo.getText().isEmpty())
		 {
			 lstUsuarios = em.createNamedQuery("Usuario.findAll",Usuario.class).getResultList();
			 
		 } 
		 else
		 {
			 int tipo= Integer.parseInt(txtTipo.getText());
			 lstUsuarios = em.createNamedQuery("Usuario.findAllxTipo",Usuario.class).setParameter("xtipo", tipo).getResultList();
			 
		 } 
		 
		 
		 
		 //muestro el listado en el txt/pdf
		 txtS.setText("Listado de Usuario\n");
		 for (Usuario u: lstUsuarios)
		 {
			 txtS.append(u.getCodigo()+"\t"+ u.getNombre() +"\t"+ u.getApellido()+"\n");
			 
		 }
		 
	}

	void consultar() {
	//obtener el código a buscar
		 int codigo = Integer.parseInt(txtCodigo.getText());
		 
		 //buscar el código en los usuarios. si existe muestro los datos.
		 
		 EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		 EntityManager em = fabrica.createEntityManager();
		 
		 Usuario  u= em.find(Usuario.class,codigo);
		 
		 if(u==null)
		 {
			 JOptionPane.showMessageDialog(this,"Usuario no registrado"); 
		 }
		 else
		 {
			 txtNombre.setText(u.getNombre());
			 txtApellido.setText(u.getApellido());
			 txtUsuario.setText(u.getUsuario());
			 txtClave.setText(u.getClave());
			 txtFechaNac.setText(u.getFnacim());
			 txtTipo.setText(u.getTipo()+"");
			 txtEstado.setText(u.getEstado()+"");
			 
			 
		 }
	
	 }

	void registro() {
		 
		 String nombre=txtNombre.getText();
		 String apellido=txtApellido.getText();
		 String usuario=txtUsuario.getText();
		 String clave=txtClave.getText();
		 String fecha =txtFechaNac.getText();
		 int tipo= Integer.parseInt(txtTipo.getText()); 
		 int estado =Integer.parseInt(txtEstado.getText());
		 
		 Usuario u = new Usuario();
		 u.setNombre(nombre);
		 u.setApellido(apellido);
		 u.setUsuario(usuario);
		 u.setClave(clave);
		 u.setFnacim(fecha);
		 u.setTipo(tipo);
		 u.setEstado(estado);

		 //proceso
		 
		 EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		 EntityManager em = fabrica.createEntityManager();
	 
		 
		 try
		 {
			 em.getTransaction().begin();
			 em.persist(u);
			 em.getTransaction().commit();
			 JOptionPane.showMessageDialog(this,"Usuario registrado");
		 }
		 catch(Exception e)
		 {
			 
			 JOptionPane.showMessageDialog(this,"error al registrar: "+ e.getMessage());
		 } 
		 finally
		 {
			 em.close();
			 
		 }
	 }
}

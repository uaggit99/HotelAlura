package views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import jdbc.controller.HuespedesController;
import jdbc.controller.ReservasController;
import jdbc.model.Huespedes;
import jdbc.model.Reservas;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHuespedes;
	private JTable tbReservas;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloHuesped;
	private JLabel labelAtras;
	private JLabel labelExit;
	int xMouse, yMouse;
	private ReservasController reservasController;
	private HuespedesController huespedesController;
	private static Integer idR;
	private static Integer idH;
	private static int id_Rer;
	public static JDateChooser fecha1;
	public static JDateChooser fecha2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Busqueda frame = new Busqueda();
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
	public Busqueda() {

		this.reservasController = new ReservasController();
		this.huespedesController = new HuespedesController();
		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);

		txtBuscar = new JTextField();
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblNewLabel_4.setBounds(331, 62, 280, 42);
		contentPane.add(lblNewLabel_4);

		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);

		tbReservas = new JTable();
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		modelo = (DefaultTableModel) tbReservas.getModel();
		modelo.addColumn("No de Reserva");
		modelo.addColumn("Fecha Check In");
		modelo.addColumn("Fecha Check Out");
		modelo.addColumn("Valor");
		modelo.addColumn("Forma de Pago");
		JScrollPane scroll_table = new JScrollPane(tbReservas);
		panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/reservado.png")), scroll_table,
				null);
		scroll_table.setVisible(true);

		tbHuespedes = new JTable();
		tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		modeloHuesped = (DefaultTableModel) tbHuespedes.getModel();
		modeloHuesped.addColumn("No de Huesped");
		modeloHuesped.addColumn("Nombre");
		modeloHuesped.addColumn("Apellido");
		modeloHuesped.addColumn("Fecha de Nacimiento");
		modeloHuesped.addColumn("Nacionalidad");
		modeloHuesped.addColumn("Telefono");
		modeloHuesped.addColumn("Número de Reserva");
		JScrollPane scroll_tableHuespedes = new JScrollPane(tbHuespedes);
		panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/pessoas.png")),
				scroll_tableHuespedes, null);
		scroll_tableHuespedes.setVisible(true);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);

		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);

			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);

		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnAtras.setBackground(Color.white);
				labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);

		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);

		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) { // Al usuario pasar el mouse por el botón este cambiará de color
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) { // Al usuario quitar el mouse por el botón este volverá al estado
													// original
				btnexit.setBackground(Color.white);
				labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);

		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);

		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);

		JPanel btnbuscar = new JPanel();
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				limpiarTabla();
				if (txtBuscar.getText().equals("")) {
					LlenarTablaHuespedes();
					LlenarTablaReservas();
				} else {
					LlenarTablaReservasId();
					LlenarTablaHuespedesId();
				}

			}

		});
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);

		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				limpiarTabla();
				if (txtBuscar.getText().equals("")) {
					LlenarTablaHuespedes();
					LlenarTablaReservas();
				} else {
					LlenarTablaReservasId();
					LlenarTablaHuespedesId();
				}
			}
		});
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));

		JPanel btnEditar = new JPanel();
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);

		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int filaReservas = tbReservas.getSelectedRow();
				int filaHuespedes = tbHuespedes.getSelectedRow();

				if (filaReservas >= 0) {
					String valoridR = tbReservas.getValueAt(filaReservas, 0).toString();
					idR = Integer.valueOf(valoridR);
					ActualizarReservas();
					limpiarTabla();
					LlenarTablaReservas();
					LlenarTablaHuespedes();
				} else if (filaHuespedes >= 0) {
					String valoridH = tbHuespedes.getValueAt(filaHuespedes, 0).toString();
					idH = Integer.valueOf(valoridH);
					String valoridRer = tbHuespedes.getValueAt(filaHuespedes, 6).toString();
					id_Rer = Integer.parseInt(valoridRer);
					
					ActualizarHuesped();
					limpiarTabla();
					LlenarTablaHuespedes();
					LlenarTablaReservas();
				}
			}
		});
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);

		JPanel btnEliminar = new JPanel();
		btnEliminar.setLayout(null);
		btnEliminar.setBackground(new Color(12, 138, 199));
		btnEliminar.setBounds(767, 508, 122, 35);
		btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEliminar);

		JLabel lblEliminar = new JLabel("ELIMINAR");
		lblEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int filaReservas = tbReservas.getSelectedRow();
				int filaHuespedes = tbHuespedes.getSelectedRow();

				if (filaReservas >= 0) {

					String reserva = tbReservas.getValueAt(filaReservas, 0).toString();
					int confirmar = JOptionPane.showConfirmDialog(null, "¿Desea Eliminar los datos?");

					if (confirmar == JOptionPane.YES_OPTION) {

						String valor = tbReservas.getValueAt(filaReservas, 0).toString();
						reservasController.Eliminar(Integer.valueOf(valor));
						JOptionPane.showMessageDialog(contentPane, "Registro Eliminado");
						limpiarTabla();
						LlenarTablaReservas();
						LlenarTablaHuespedes();
					}
				}

				else if (filaHuespedes >= 0) {

					String huespedes = tbHuespedes.getValueAt(filaHuespedes, 0).toString();
					int confirmarh = JOptionPane.showConfirmDialog(null, "¿Desea Eliminar los datos?");

					if (confirmarh == JOptionPane.YES_OPTION) {

						String valor = tbHuespedes.getValueAt(filaHuespedes, 0).toString();
						huespedesController.Eliminar(Integer.valueOf(valor));
						JOptionPane.showMessageDialog(contentPane, "Registro Eliminado");
						limpiarTabla();
						LlenarTablaHuespedes();
						LlenarTablaReservas();
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"Error fila no seleccionada, por favor realice una busqueda y seleccione una fila para eliminar");
				}
			}
		});
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setForeground(Color.WHITE);
		lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEliminar.setBounds(0, 0, 122, 35);
		btnEliminar.add(lblEliminar);
		setResizable(false);
	}

	private List<Reservas> buscarReservas() {
		return this.reservasController.buscar();
		// TODO Auto-generated method stub

	}

	private List<Huespedes> buscarHuespedes() {
		return this.huespedesController.listarHuespedes();
	}

	private List<Reservas> BuscarReservasId() {
		return this.reservasController.buscarId(txtBuscar.getText());
	}

	private List<Huespedes> BuscarHuespedesId() {
		return this.huespedesController.listarHuespedesId(txtBuscar.getText());
	}

	private void LlenarTablaReservas() {

		// Llenar tabla
		List<Reservas> reserva = buscarReservas();
		try {
			for (Reservas reservas : reserva) {
				modelo.addRow(new Object[] { reservas.getId(), reservas.getFechaEntrada(), reservas.getFechaSalida(),
						reservas.getValor(), reservas.getFormaPago() });
			}
		} catch (Exception e) {
			throw e;
		}
	}

	private void LlenarTablaHuespedes() {
		// Llenar Tabla
		List<Huespedes> huesped = buscarHuespedes();
		try {
			for (Huespedes huespedes : huesped) {
				modeloHuesped.addRow(new Object[] { huespedes.getId(), huespedes.getNombre(), huespedes.getApellido(),
						huespedes.getFechaNacido(), huespedes.getNacionalidad(), huespedes.getTelefono(),
						huespedes.getReserva_id() });
			}
		} catch (Exception e) {
			throw e;
		}
	}

	private void LlenarTablaReservasId() {

		// Llenar tabla
		List<Reservas> reserva = BuscarReservasId();
		try {
			for (Reservas reservas : reserva) {
				modelo.addRow(new Object[] { reservas.getId(), reservas.getFechaEntrada(), reservas.getFechaSalida(),
						reservas.getValor(), reservas.getFormaPago() });
			}
		} catch (Exception e) {
			throw e;
		}
	}

	private void limpiarTabla() {
		((DefaultTableModel) tbHuespedes.getModel()).setRowCount(0);
		((DefaultTableModel) tbReservas.getModel()).setRowCount(0);
	}

	private void LlenarTablaHuespedesId() {
		// Llenar Tabla
		List<Huespedes> huesped = BuscarHuespedesId();
		try {
			for (Huespedes huespedes : huesped) {
				modeloHuesped.addRow(new Object[] { huespedes.getId(), huespedes.getNombre(), huespedes.getApellido(),
						huespedes.getFechaNacido(), huespedes.getNacionalidad(), huespedes.getTelefono(),
						huespedes.getReserva_id() });
			}
		} catch (Exception e) {
			throw e;
		}
	}

	private void ActualizarReservas() {

		Optional.ofNullable(modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
				.ifPresentOrElse(fila -> {

					Date fechaE = Date.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 1).toString());
					Date fechaS = Date.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 2).toString());
					Float valor = Float.parseFloat(modelo.getValueAt(tbReservas.getSelectedRow(), 3).toString());
					String formaPago = (String) modelo.getValueAt(tbReservas.getSelectedRow(), 4);
					Integer id = Integer.parseInt(modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString());
					Reservas datos = new Reservas(fechaE, fechaS, valor, formaPago, id);

					ReservasView reserva = new ReservasView();
					fecha1 = new JDateChooser(new java.sql.Date(fechaE.getTime()));
					fecha2 = new JDateChooser(new java.sql.Date(fechaS.getTime()));

					reserva.calcular(fecha1, fecha2);
					String datoValor = reserva.valorE;
					if (datoValor != "") {
						float valore = Float.parseFloat(datoValor);

						this.reservasController.Actualizar(fechaE, fechaS, valore, formaPago, idR);
					}
					JOptionPane.showMessageDialog(this, String.format("Registro modificado con éxito"));

				},( )-> JOptionPane.showMessageDialog( this,("Por favor, elije un registro")));

	}

	private void ActualizarHuesped() {
		Optional.ofNullable(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))
				.ifPresentOrElse(filaHuesped -> {

					String nombre = (String) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 1);
					String apellido = (String) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 2);
					Date fechaN = Date.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 3).toString());
					String nacionalidad = (String) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 4);
					String telefono = (String) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 5);
					int idReserva = id_Rer;//Integer	.parseInt(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 6).toString());
					Integer id = Integer.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());
					this.huespedesController.Actualizar(nombre, apellido, fechaN, nacionalidad, telefono,idH);
					JOptionPane.showMessageDialog(this, String.format("Registro modificado con éxito"));
				}, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un registro"));

	}

	// Código que permite mover la ventana por la pantalla según la posición de "x"
	// y "y"
	private void headerMousePressed(java.awt.event.MouseEvent evt) {
		xMouse = evt.getX();
		yMouse = evt.getY();
	}

	private void headerMouseDragged(java.awt.event.MouseEvent evt) {
		int x = evt.getXOnScreen();
		int y = evt.getYOnScreen();
		this.setLocation(x - xMouse, y - yMouse);
	}
}

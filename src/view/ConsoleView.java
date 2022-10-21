package view;

import java.util.Scanner;

import javax.swing.JOptionPane;

import controller.ContatoController;
import model.Contato;

public class ConsoleView {

	public static void main(String[] args) {

		// instância da camada Controller.
		ContatoController ctrl = new ContatoController();
		Scanner in = new Scanner(System.in);
		int option;
		do {
			System.out.println(" ====== \t AGENDA DE CONTATOS    \t   ====== ");
			System.out.println("1 - Exibir todos | 2 - Incluir | 3 - Editar | 4 - Deletar \t 0 - Sair");
			option = in.nextInt();
			in.nextLine();
			switch (option) {
			case 1:
				System.out.println(" =============== Exibindo contatos: =============== ");
				System.out.printf("%3s   %-20s %s \n", "ID", "Nome", "Idade");
				for (Contato c : ctrl.obterTodos())
					System.out.printf("%3d - %-20s %d \n", c.getId(), c.getNome(), c.getIdade());
				System.out.println(" =============== Fim da lista =============== \n");
				break;
			case 2:
				System.out.println(" =========== Incluir novo contato =========== ");
				System.out.println("Informe os dados abaixo:");
				Contato c1 = new Contato();
				System.out.println("Nome: ");
				c1.setNome(in.nextLine());
				System.out.println("Idade: ");
				c1.setIdade(in.nextInt());
				try {
					ctrl.inserirContato(c1);
					System.out.println(" ========= Contato salvo na lista ========= \n ");
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
				break;
			case 3:
				System.out.println(" ============ Editar contato ============ ");
				System.out.println("Informe o ID:");
				try {
					int idAtual = in.nextInt();
					in.nextLine();
					Contato edit = ctrl.obterById(idAtual);
					System.out.printf("Nome (ATUAL = %s):\n", edit.getNome());
					edit.setNome(in.nextLine());
					System.out.printf("Idade (ATUAL = %d):\n", edit.getIdade());
					edit.setIdade(in.nextInt());
					ctrl.alterarContato(edit);
					System.out.println(" =========== Alteração realizada =========== \n");
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
				break;
			case 4:
				System.out.println(" ============ Excluir contato ============ ");
				System.out.println("Informe o ID: ");
				int id = in.nextInt();
				try {
					ctrl.excluirContato(id);
					System.out.println(" ==================== Excluído =================== \n");
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
				break;
			}
		} while (option != 0);
		System.out.println(" ====== APLICAÇÃO ENCERRADA ====== ");
		in.close();
	}
}
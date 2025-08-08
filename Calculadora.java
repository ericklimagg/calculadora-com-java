import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculadora extends JFrame implements ActionListener {

    JTextField tela;
    double valor1 = 0, valor2 = 0;
    char operacao;
    boolean operadorSelecionado = false;

    JButton[] numeros = new JButton[10];
    JButton somar, subtrair, multiplicar, dividir, igual, ponto, limpar, apagar;

    public Calculadora() {
        setTitle("Calculadora");
        setSize(350, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        tela = new JTextField();
        tela.setBounds(20, 20, 300, 40);
        tela.setFont(new Font("Arial", Font.PLAIN, 20));
        tela.setEditable(false);
        add(tela);

        somar = new JButton("+");
        subtrair = new JButton("-");
        multiplicar = new JButton("*");
        dividir = new JButton("/");
        igual = new JButton("=");
        ponto = new JButton(".");
        limpar = new JButton("C");
        apagar = new JButton("←");

        JButton[] funcoes = {somar, subtrair, multiplicar, dividir, igual, ponto, limpar, apagar};
        for (JButton b : funcoes) {
            b.addActionListener(this);
        }

        for (int i = 0; i < 10; i++) {
            numeros[i] = new JButton(String.valueOf(i));
            numeros[i].addActionListener(this);
        }

        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(4, 4, 10, 10));
        painel.setBounds(20, 80, 300, 250);

        painel.add(numeros[7]);
        painel.add(numeros[8]);
        painel.add(numeros[9]);
        painel.add(somar);

        painel.add(numeros[4]);
        painel.add(numeros[5]);
        painel.add(numeros[6]);
        painel.add(subtrair);

        painel.add(numeros[1]);
        painel.add(numeros[2]);
        painel.add(numeros[3]);
        painel.add(multiplicar);

        painel.add(ponto);
        painel.add(numeros[0]);
        painel.add(igual);
        painel.add(dividir);

        add(painel);

        limpar.setBounds(20, 340, 140, 40);
        apagar.setBounds(180, 340, 140, 40);
        add(limpar);
        add(apagar);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numeros[i]) {
                tela.setText(tela.getText() + i);
            }
        }

        if (e.getSource() == ponto) {
            tela.setText(tela.getText() + ".");
        }

        if (e.getSource() == somar || e.getSource() == subtrair ||
            e.getSource() == multiplicar || e.getSource() == dividir) {

            if (!operadorSelecionado && !tela.getText().isEmpty()) {
                valor1 = Double.parseDouble(tela.getText());
                operacao = ((JButton) e.getSource()).getText().charAt(0);
                tela.setText(tela.getText() + " " + operacao + " ");
                operadorSelecionado = true;
            }
        }

        if (e.getSource() == igual) {
            try {
                String[] partes = tela.getText().split(" ");
                if (partes.length < 3) return;

                valor1 = Double.parseDouble(partes[0]);
                operacao = partes[1].charAt(0);
                valor2 = Double.parseDouble(partes[2]);

                double resultado = 0;
                switch (operacao) {
                    case '+': resultado = valor1 + valor2; break;
                    case '-': resultado = valor1 - valor2; break;
                    case '*': resultado = valor1 * valor2; break;
                    case '/':
                        if (valor2 == 0) {
                            tela.setText("Erro: divisão por zero");
                            operadorSelecionado = false;
                            return;
                        }
                        resultado = valor1 / valor2;
                        break;
                }

                tela.setText(String.valueOf(resultado));
                operadorSelecionado = false;
            } catch (Exception ex) {
                tela.setText("Erro");
            }
        }

        if (e.getSource() == limpar) {
            tela.setText("");
            operadorSelecionado = false;
        }

        if (e.getSource() == apagar) {
            String txt = tela.getText();
            if (!txt.isEmpty()) {
                tela.setText(txt.substring(0, txt.length() - 1));
            }
        }
    }

    public static void main(String[] args) {
        new Calculadora();
    }
}

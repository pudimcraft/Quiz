package com.pudimcraft.robert.quizunivale;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Quiz extends JFrame implements ActionListener {

	


	private static final long serialVersionUID = -8241137080588358782L;
	
	JLabel labelPergunta;
	JButton botaoA;
	JButton botaoB;
	JButton botaoC;
	JButton botaoNext;
	JButton botaoPrev;
	JLabel labelContador;
	JLabel labelBaixo;
	int acertos = 0;
	int counter = 0;
	Problema[] probList = new Problema[5];// array para adicionar perguntas facilmente
	
	public static void main(String[] args){
		new Quiz();
	} 
	
	public Quiz(){
		super("Quiz UNIVALE");
		arrumarMenu();
		registerListeners();
	    carregarPerguntas();
	    atualizarTela();
	} 
	
	public void arrumarMenu(){
		
		JPanel painelPerguntas = new JPanel();
		labelPergunta = new JLabel("pergunta");
		botaoA = new JButton("resposta A");
		botaoB = new JButton("resposta B");
		botaoC = new JButton("resposta C");
		
		JPanel painelPrincipal = new JPanel();
		botaoPrev = new JButton("<-");
		labelContador = new JLabel("0");
		labelBaixo = new JLabel("Clique na resposta Correta!");
		botaoNext = new JButton("->");
		
		
		painelPerguntas.setLayout(new GridLayout(0,1));
		painelPerguntas.add(labelPergunta);
		painelPerguntas.add(botaoA);
		painelPerguntas.add(botaoB);
		painelPerguntas.add(botaoC);
		
		painelPrincipal.setLayout(new FlowLayout());
		painelPrincipal.add(botaoPrev);
		painelPrincipal.add(labelContador);
		painelPrincipal.add(labelBaixo);
		painelPrincipal.add(botaoNext);
		
		
		Container mainPanel = this.getContentPane();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(painelPerguntas, BorderLayout.CENTER);
		mainPanel.add(painelPrincipal, BorderLayout.SOUTH);
		
	
		this.setSize(500, 300);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void registerListeners(){
		botaoA.addActionListener(this);
		botaoB.addActionListener(this);
		botaoC.addActionListener(this);
		botaoPrev.addActionListener(this);
		botaoNext.addActionListener(this);
	}
	
	public void carregarPerguntas(){
		
		
		probList[0] = new Problema(
				  "Qual a cor do olho de Napoleão Bonaparte?",
				  "Azul",
				  "Castanho",
				  "Verde",
				  "C"
				);
		probList[1] = new Problema(
				  "Qual a capital da Polônia?",
				  "Cracóvia",
				  "Varsóvia",
				  "Katowice",
				  "B"
				);
		probList[2] = new Problema(
				  "Qual é a primeira lingua falada no Paquistao?",
				  "Arabe",
				  "Inglês",
				  "Urdu",
				  "C"
				);
		probList[3] = new Problema(
				  "Quem foi o presidente do Brasil em 1970?",
				  "Emílio Garrastazu",
				  "João Goulart",
				  "Tancredo Neves",
				  "A"
				);
		probList[4] = new Problema(
				  "Quais dos filmes abaixo foram inspirados/gravados na frança?",
				  "Dama e o Vagabundo",
				  "Lala land",
				  " Aristogatos",
				  "C"
				);
	} 
	
	public void actionPerformed(ActionEvent e){
		System.out.println(e.getActionCommand());

	   if (e.getSource() == botaoA){
			checkAns("A");
		} else if (e.getSource() == botaoB){
			checkAns("B");
		} else if (e.getSource() == botaoC){
			checkAns("C");
		} else if (e.getSource() == botaoPrev){
			prevQuestion();
		} else if (e.getSource() == botaoNext){
			nextQuestion();
		} else {
			labelBaixo.setText("erro");
		} 
		
		
	} 
	
	public void checkAns(String respostaJogador){
		String correct = probList[counter].getCorrect();
		if (respostaJogador.equals(correct)){
			labelBaixo.setText("Acertou!");	
			nextQuestion();
			acertos++;
		} else {
			labelBaixo.setText("ERROU!");
			nextQuestion();
		} 
	} 
	
	public void prevQuestion(){
		
		counter--;
		if (counter < 0){
			counter = 0;
		} 
		atualizarTela();
	} 
	
	public void nextQuestion(){
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		counter++;
		if (counter >= probList.length){
			counter = probList.length - 1;
		} 
		atualizarTela();
	}
	
	public void atualizarTela(){
		//atualiza o problema atual
		labelContador.setText("Acertos:" + String.valueOf(acertos) + "/" + probList.length + " | Questao N°: " + String.valueOf(counter));
		Problema p = probList[counter];
		labelPergunta.setText(p.getQuestion());
		botaoA.setText(p.getAnsA());
		botaoB.setText(p.getAnsB());
		botaoC.setText(p.getAnsC());
		labelBaixo.setText("");
	}
	
} 

class Problema {

	private String pergunta;
	private String respostaA;
	private String respostaB;
	private String respostaC;
	private String respostaCorreta;
	
	//construtor precisa de todos os valores para gerar o problema
	public Problema (String tQuestion, String tAnsA, String tAnsB,
			        String tAnsC, String tCorrect){
		pergunta = tQuestion;
		respostaA = tAnsA;
		respostaB = tAnsB;
		respostaC = tAnsC;
		respostaCorreta = tCorrect;
	}

	//getters do problema
	String getQuestion(){
		return pergunta;
	} 
	
	String getAnsA(){
		return respostaA;
	} 
	
	String getAnsB(){
		return respostaB;
	}
	
	String getAnsC(){
		return respostaC;
	} 
	
	String getCorrect(){
		return respostaCorreta;
	} 
	
} 
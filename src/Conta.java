import java.util.Scanner;
public class Conta {
    Scanner entrada = new Scanner(System.in);
    public static int iNumContas = 0;


    private Cliente nome;
    private int numConta;
    private double saldo;
    private double limite;
    private Operacao[] operacoes = new Operacao[1000];
    public int nOperacoes = 0;


    public Conta(int numConta, double saldo, double limite, Cliente nome){
        this.numConta = numConta;
        this.saldo = saldo;
        this.limite = saldo*2;
        this.nome = nome;

        Conta.iNumContas ++;
    }

    void imprimir(){
        System.out.println("....Número da conta: " + numConta);
        System.out.println("....Nome: " + this.nome.getNome());
        System.out.println("....CPF: " + this.nome.getEndereco());
        System.out.println("....Saldo: " + saldo);
        System.out.println("....Limite: " +limite);
    }

    public boolean sacar(double valor){
        if(valor > this.saldo || valor < 0){
            return false;
        } else {
            this.saldo -= valor;
            if(this.nOperacoes < 1000) {
                this.operacoes[this.nOperacoes] = new OperacaoSaque(valor);
                this.nOperacoes++;
            }
            return true;
        }
    }

    public boolean depositar(double valor) {
        if(valor < 0){
            return  false;
        }else{
            this.saldo += valor;
            if(this.nOperacoes < 1000) {
                this.operacoes[this.nOperacoes] = new OperacaoDeposito(valor);
                this.nOperacoes++;
            }
        }

        return  true;
    }

    public boolean transferir(Conta destino, double valor){

        if(this.sacar(valor)){
            destino.depositar(valor);
            return true;
        } else {
            return  false;
        }
    }

    public void extrato() {
        int i;
        System.out.println("Extrato");
        for (i = 0; i < nOperacoes; i++) {
            System.out.println(this.operacoes[i].getData()+"\t"+this.operacoes[i].getTipo()+"\t"+this.operacoes[i].getValor());
        }
    }

    public String toString(){
        return nome.toString() + "Dados do Cliente\t: " + "Número da conta: " + this.getNumConta() +"\nLimite: " +this.getLimite()
                + "\nSaldo: " +this.getSaldo() +"\nOperações realizadas: " +this.nOperacoes;
    }


    public boolean equals(Object obj){
        if(obj instanceof Conta){
            Conta conta = (Conta) obj;
            if(this.numConta == conta.numConta);
                return true;
        }else{
            return false;
        }
    }

    public int getNumConta() {
        return numConta;
    }

    public void setNumConta(int numConta) {
        this.numConta = numConta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getLimite(){
        return limite;
    }

    public void setLimite(){
        this.limite = limite;
    }


    public Cliente getNome() {
        return nome;
    }

    public void setNome(Cliente nome) {
        this.nome = nome;
    }
}
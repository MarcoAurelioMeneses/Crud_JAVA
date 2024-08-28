package br.com.marco.springApi.domain.endereco;


import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {
    private String logradouro;
    private String bairro;
    private String cidade;
    private String numero;
    private String uf;
    private String cep;
    private String complemento;

    public Endereco(DadosEndereco dados) {
        this.logradouro = dados.logradouro();
        this.bairro = dados.bairro();
        this.cidade = dados.cidade();
        this.uf = dados.uf();
        this.cep = dados.cep();
        this.complemento = dados.complemento();
        this.numero = dados.numero();
    }

    public void atulizarInformacoes(DadosEndereco dados) {
        if (dados.logradouro() != null){
            this.logradouro = dados.logradouro();
        }

        if (dados.bairro() != null){
            this.bairro = dados.bairro();
        }

        if (dados.cidade() != null){
            this.cidade = dados.cidade();
        }

        if (dados.uf() != null){
            this.uf = dados.uf();
        }

        if (dados.cep() != null){
            this.cep = dados.cep();
        }

        if (dados.complemento() != null){
            this.complemento = dados.complemento();
        }

        if (dados.numero() != null){
            this.numero = dados.numero();
        }
    }
}

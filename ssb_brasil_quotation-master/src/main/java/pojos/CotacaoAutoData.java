package pojos;

import java.util.Objects;

/**
 * POJO para dados de cotação de seguros de automóveis.
 * 
 * Utiliza Builder Pattern para:
 * - Construção fluente e legível
 * - Validações intermediárias
 * - Imutabilidade após construção
 * - Melhor manutenibilidade
 * 
 * Padrão E → E → S:
 * ENTRADA: Dados parciais do Excel
 * EXECUÇÃO: Builder valida e constrói
 * SAÍDA: Objeto imutável e validado
 */
public class CotacaoAutoData {
    
    // ========================================================================
    // ATRIBUTOS - Imutáveis após construção
    // ========================================================================
    
    private final String cenario;
    private final String corretor;
    private final String tipoSeguro;
    private final String numeroCI;
    private final String classeBonus;
    private final String cpfcnpj;
    private final String chassi;
    private final String pacoteCoberturas;
    private final String email;
    private final String telefone;
    private final String celular;
    private final String veiculo0Km;
    private final String cep;
    private final String cpfCondutor;
    private final String estadoCivil;
    private final String usoComercial;
    private final String finalidadeUso;
    private final String motoristaIndeterminado;
    private final String blindagem;
    private final String valorBlindagem;
    private final String condutor18a25;
    private final String tipoResidencia;
    private final String franquia;
    private final String assistencia24h;
    private final String carroReserva;
    private final String vidros;
    private final String erroEsperado;
    private final String acessorios;
    private final String acessoriosValor;
    private final String condutorTrabalha;
    private final String utilizaIrTrabalho;
    private final String garagemTrabalho;
    private final String condutorEstuda;
    private final String utilizaIrEstudo;
    private final String garagemEscola;
    private final String garagemResidencia;
    private final String transportadora;
    private final String motoristaPrincipal;
    private final String seguroCarga;
    private final String alienacao;
    private final String regiaoCirculacao;
    private final String garagemServico;
    private final String dirige22h;
    private final String possuiGerenciamentoRisco;
    private final String tipoGerenciamentoRisco;
    private final String cargaFrequente;
    private final String profissao;
    private final String renda;
    private final String dataSaida;
    private final String numEndereco;
    
    // ========================================================================
    // CONSTRUTOR PRIVADO - Apenas Builder pode criar
    // ========================================================================
    
    private CotacaoAutoData(Builder builder) {
        this.cenario = builder.cenario;
        this.corretor = builder.corretor;
        this.tipoSeguro = builder.tipoSeguro;
        this.numeroCI = builder.numeroCI;
        this.classeBonus = builder.classeBonus;
        this.cpfcnpj = builder.cpfcnpj;
        this.chassi = builder.chassi;
        this.pacoteCoberturas = builder.pacoteCoberturas;
        this.email = builder.email;
        this.telefone = builder.telefone;
        this.celular = builder.celular;
        this.veiculo0Km = builder.veiculo0Km;
        this.cep = builder.cep;
        this.cpfCondutor = builder.cpfCondutor;
        this.estadoCivil = builder.estadoCivil;
        this.usoComercial = builder.usoComercial;
        this.finalidadeUso = builder.finalidadeUso;
        this.motoristaIndeterminado = builder.motoristaIndeterminado;
        this.blindagem = builder.blindagem;
        this.valorBlindagem = builder.valorBlindagem;
        this.condutor18a25 = builder.condutor18a25;
        this.tipoResidencia = builder.tipoResidencia;
        this.franquia = builder.franquia;
        this.assistencia24h = builder.assistencia24h;
        this.carroReserva = builder.carroReserva;
        this.vidros = builder.vidros;
        this.erroEsperado = builder.erroEsperado;
        this.acessorios = builder.acessorios;
        this.acessoriosValor = builder.acessoriosValor;
        this.condutorTrabalha = builder.condutorTrabalha;
        this.utilizaIrTrabalho = builder.utilizaIrTrabalho;
        this.garagemTrabalho = builder.garagemTrabalho;
        this.condutorEstuda = builder.condutorEstuda;
        this.utilizaIrEstudo = builder.utilizaIrEstudo;
        this.garagemEscola = builder.garagemEscola;
        this.garagemResidencia = builder.garagemResidencia;
        this.transportadora = builder.transportadora;
        this.motoristaPrincipal = builder.motoristaPrincipal;
        this.seguroCarga = builder.seguroCarga;
        this.alienacao = builder.alienacao;
        this.regiaoCirculacao = builder.regiaoCirculacao;
        this.garagemServico = builder.garagemServico;
        this.dirige22h = builder.dirige22h;
        this.possuiGerenciamentoRisco = builder.possuiGerenciamentoRisco;
        this.tipoGerenciamentoRisco = builder.tipoGerenciamentoRisco;
        this.cargaFrequente = builder.cargaFrequente;
        this.profissao = builder.profissao;
        this.renda = builder.renda;
        this.dataSaida = builder.dataSaida;
        this.numEndereco = builder.numEndereco;
        
        // Validações finais
        validar();
    }
    
    // ========================================================================
    // VALIDAÇÕES
    // ========================================================================
    
    private void validar() {
        // Validações obrigatórias
        Objects.requireNonNull(chassi, "Chassi é obrigatório");
        Objects.requireNonNull(cpfcnpj, "CPF/CNPJ é obrigatório");
        
        // Validações de formato
        if (cpfcnpj != null && !cpfcnpj.matches("\\d{11}|\\d{14}")) {
            throw new IllegalArgumentException("CPF/CNPJ deve conter 11 ou 14 dígitos");
        }
        
        if (chassi != null && chassi.length() != 17) {
            throw new IllegalArgumentException("Chassi deve conter 17 caracteres");
        }
    }
    
    // ========================================================================
    // GETTERS - Apenas leitura
    // ========================================================================
    
    public String getCenario() { return cenario; }
    public String getCorretor() { return corretor; }
    public String getTipoSeguro() { return tipoSeguro; }
    public String getNumeroCI() { return numeroCI; }
    public String getClasseBonus() { return classeBonus; }
    public String getCpfcnpj() { return cpfcnpj; }
    public String getChassi() { return chassi; }
    public String getPacoteCoberturas() { return pacoteCoberturas; }
    public String getEmail() { return email; }
    public String getTelefone() { return telefone; }
    public String getCelular() { return celular; }
    public String getVeiculo0Km() { return veiculo0Km; }
    public String getCep() { return cep; }
    public String getCpfCondutor() { return cpfCondutor; }
    public String getEstadoCivil() { return estadoCivil; }
    public String getUsoComercial() { return usoComercial; }
    public String getFinalidadeUso() { return finalidadeUso; }
    public String getMotoristaIndeterminado() { return motoristaIndeterminado; }
    public String getBlindagem() { return blindagem; }
    public String getValorBlindagem() { return valorBlindagem; }
    public String getCondutor18a25() { return condutor18a25; }
    public String getTipoResidencia() { return tipoResidencia; }
    public String getFranquia() { return franquia; }
    public String getAssistencia24h() { return assistencia24h; }
    public String getCarroReserva() { return carroReserva; }
    public String getVidros() { return vidros; }
    public String getErroEsperado() { return erroEsperado; }
    public String getAcessorios() { return acessorios; }
    public String getAcessoriosValor() { return acessoriosValor; }
    public String getCondutorTrabalha() { return condutorTrabalha; }
    public String getUtilizaIrTrabalho() { return utilizaIrTrabalho; }
    public String getGaragemTrabalho() { return garagemTrabalho; }
    public String getCondutorEstuda() { return condutorEstuda; }
    public String getUtilizaIrEstudo() { return utilizaIrEstudo; }
    public String getGaragemEscola() { return garagemEscola; }
    public String getGaragemResidencia() { return garagemResidencia; }
    public String getTransportadora() { return transportadora; }
    public String getMotoristaPrincipal() { return motoristaPrincipal; }
    public String getSeguroCarga() { return seguroCarga; }
    public String getAlienacao() { return alienacao; }
    public String getRegiaoCirculacao() { return regiaoCirculacao; }
    public String getGaragemServico() { return garagemServico; }
    public String getDirige22h() { return dirige22h; }
    public String getPossuiGerenciamentoRisco() { return possuiGerenciamentoRisco; }
    public String getTipoGerenciamentoRisco() { return tipoGerenciamentoRisco; }
    public String getCargaFrequente() { return cargaFrequente; }
    public String getProfissao() { return profissao; }
    public String getRenda() { return renda; }
    public String getDataSaida() { return dataSaida; }
    public String getNumEndereco() { return numEndereco; }
    
    // ========================================================================
    // BUILDER PATTERN
    // ========================================================================
    
    public static Builder builder() {
        return new Builder();
    }
    
    public static class Builder {
        private String cenario;
        private String corretor;
        private String tipoSeguro;
        private String numeroCI;
        private String classeBonus;
        private String cpfcnpj;
        private String chassi;
        private String pacoteCoberturas;
        private String email;
        private String telefone;
        private String celular;
        private String veiculo0Km;
        private String cep;
        private String cpfCondutor;
        private String estadoCivil;
        private String usoComercial;
        private String finalidadeUso;
        private String motoristaIndeterminado;
        private String blindagem;
        private String valorBlindagem;
        private String condutor18a25;
        private String tipoResidencia;
        private String franquia;
        private String assistencia24h;
        private String carroReserva;
        private String vidros;
        private String erroEsperado;
        private String acessorios;
        private String acessoriosValor;
        private String condutorTrabalha;
        private String utilizaIrTrabalho;
        private String garagemTrabalho;
        private String condutorEstuda;
        private String utilizaIrEstudo;
        private String garagemEscola;
        private String garagemResidencia;
        private String transportadora;
        private String motoristaPrincipal;
        private String seguroCarga;
        private String alienacao;
        private String regiaoCirculacao;
        private String garagemServico;
        private String dirige22h;
        private String possuiGerenciamentoRisco;
        private String tipoGerenciamentoRisco;
        private String cargaFrequente;
        private String profissao;
        private String renda;
        private String dataSaida;
        private String numEndereco;
        
        private Builder() {}
        
        public Builder cenario(String cenario) { this.cenario = cenario; return this; }
        public Builder corretor(String corretor) { this.corretor = corretor; return this; }
        public Builder tipoSeguro(String tipoSeguro) { this.tipoSeguro = tipoSeguro; return this; }
        public Builder numeroCI(String numeroCI) { this.numeroCI = numeroCI; return this; }
        public Builder classeBonus(String classeBonus) { this.classeBonus = classeBonus; return this; }
        public Builder cpfcnpj(String cpfcnpj) { this.cpfcnpj = cpfcnpj; return this; }
        public Builder chassi(String chassi) { this.chassi = chassi; return this; }
        public Builder pacoteCoberturas(String pacoteCoberturas) { this.pacoteCoberturas = pacoteCoberturas; return this; }
        public Builder email(String email) { this.email = email; return this; }
        public Builder telefone(String telefone) { this.telefone = telefone; return this; }
        public Builder celular(String celular) { this.celular = celular; return this; }
        public Builder veiculo0Km(String veiculo0Km) { this.veiculo0Km = veiculo0Km; return this; }
        public Builder cep(String cep) { this.cep = cep; return this; }
        public Builder cpfCondutor(String cpfCondutor) { this.cpfCondutor = cpfCondutor; return this; }
        public Builder estadoCivil(String estadoCivil) { this.estadoCivil = estadoCivil; return this; }
        public Builder usoComercial(String usoComercial) { this.usoComercial = usoComercial; return this; }
        public Builder finalidadeUso(String finalidadeUso) { this.finalidadeUso = finalidadeUso; return this; }
        public Builder motoristaIndeterminado(String motoristaIndeterminado) { this.motoristaIndeterminado = motoristaIndeterminado; return this; }
        public Builder blindagem(String blindagem) { this.blindagem = blindagem; return this; }
        public Builder valorBlindagem(String valorBlindagem) { this.valorBlindagem = valorBlindagem; return this; }
        public Builder condutor18a25(String condutor18a25) { this.condutor18a25 = condutor18a25; return this; }
        public Builder tipoResidencia(String tipoResidencia) { this.tipoResidencia = tipoResidencia; return this; }
        public Builder franquia(String franquia) { this.franquia = franquia; return this; }
        public Builder assistencia24h(String assistencia24h) { this.assistencia24h = assistencia24h; return this; }
        public Builder carroReserva(String carroReserva) { this.carroReserva = carroReserva; return this; }
        public Builder vidros(String vidros) { this.vidros = vidros; return this; }
        public Builder erroEsperado(String erroEsperado) { this.erroEsperado = erroEsperado; return this; }
        public Builder acessorios(String acessorios) { this.acessorios = acessorios; return this; }
        public Builder acessoriosValor(String acessoriosValor) { this.acessoriosValor = acessoriosValor; return this; }
        public Builder condutorTrabalha(String condutorTrabalha) { this.condutorTrabalha = condutorTrabalha; return this; }
        public Builder utilizaIrTrabalho(String utilizaIrTrabalho) { this.utilizaIrTrabalho = utilizaIrTrabalho; return this; }
        public Builder garagemTrabalho(String garagemTrabalho) { this.garagemTrabalho = garagemTrabalho; return this; }
        public Builder condutorEstuda(String condutorEstuda) { this.condutorEstuda = condutorEstuda; return this; }
        public Builder utilizaIrEstudo(String utilizaIrEstudo) { this.utilizaIrEstudo = utilizaIrEstudo; return this; }
        public Builder garagemEscola(String garagemEscola) { this.garagemEscola = garagemEscola; return this; }
        public Builder garagemResidencia(String garagemResidencia) { this.garagemResidencia = garagemResidencia; return this; }
        public Builder transportadora(String transportadora) { this.transportadora = transportadora; return this; }
        public Builder motoristaPrincipal(String motoristaPrincipal) { this.motoristaPrincipal = motoristaPrincipal; return this; }
        public Builder seguroCarga(String seguroCarga) { this.seguroCarga = seguroCarga; return this; }
        public Builder alienacao(String alienacao) { this.alienacao = alienacao; return this; }
        public Builder regiaoCirculacao(String regiaoCirculacao) { this.regiaoCirculacao = regiaoCirculacao; return this; }
        public Builder garagemServico(String garagemServico) { this.garagemServico = garagemServico; return this; }
        public Builder dirige22h(String dirige22h) { this.dirige22h = dirige22h; return this; }
        public Builder possuiGerenciamentoRisco(String possuiGerenciamentoRisco) { this.possuiGerenciamentoRisco = possuiGerenciamentoRisco; return this; }
        public Builder tipoGerenciamentoRisco(String tipoGerenciamentoRisco) { this.tipoGerenciamentoRisco = tipoGerenciamentoRisco; return this; }
        public Builder cargaFrequente(String cargaFrequente) { this.cargaFrequente = cargaFrequente; return this; }
        public Builder profissao(String profissao) { this.profissao = profissao; return this; }
        public Builder renda(String renda) { this.renda = renda; return this; }
        public Builder dataSaida(String dataSaida) { this.dataSaida = dataSaida; return this; }
        public Builder numEndereco(String numEndereco) { this.numEndereco = numEndereco; return this; }
        
        /**
         * Constrói o objeto CotacaoAutoData com validações.
         * @return Objeto CotacaoAutoData imutável
         * @throws IllegalArgumentException se validações falharem
         */
        public CotacaoAutoData build() {
            return new CotacaoAutoData(this);
        }
    }
    
    // ========================================================================
    // EQUALS, HASHCODE E TOSTRING
    // ========================================================================
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CotacaoAutoData that = (CotacaoAutoData) o;
        return Objects.equals(chassi, that.chassi) &&
               Objects.equals(cpfcnpj, that.cpfcnpj);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(chassi, cpfcnpj);
    }
    
    @Override
    public String toString() {
        return String.format("CotacaoAutoData[chassi=%s, cpf=%s, cenario=%s]", 
            chassi, cpfcnpj, cenario);
    }
}

package data;

public class VidaData {
    private String cenario;
    private String corretor;
    private String cnpj_Cpf;
    private String nomeSeguradoEstipulante;
    private String ramoAtividade;
    private String dataInicioVigencia;
    private String tipoSeguro;
    private String nmrVidasEmpregados;
    private String capital_Individual_Empregados;
    private String nmrVidasSocios;
    private String capital_Individual_Socios;
    private String cobertura;
    private String assistencia;
    private String coberturaAssistencia;
    private String vlrAssistencia;
    private String comissao;
    private String agenciamento;
    private String formaPagamento;
    private String qtdParcelas;
    private String email;
    private String celular;
    private String cep;
    private String endereco;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String diaPagamento;
    private String Resultado_Esperado;


    //GETS


    public String getCenario() {
        return cenario;
    }

    public String getCorretor() {
        return corretor;
    }

    public String getTipoSeguro() {
        return tipoSeguro;
    }

    public String getCnpj_Cpf() {
        return cnpj_Cpf;
    }

    public String getNomeSeguradoEstipulante() {
        return nomeSeguradoEstipulante;
    }

    public String getRamoAtividade() {
        return ramoAtividade;
    }

    public String getDataInicioVigencia() {
        return dataInicioVigencia;
    }

    public String getNmrVidasEmpregados() {
        return nmrVidasEmpregados;
    }

    public String getCapital_Individual_Empregados() {
        return capital_Individual_Empregados;
    }

    public String getNmrVidasSocios() {
        return nmrVidasSocios;
    }

    public String getCapital_Individual_Socios() {
        return capital_Individual_Socios;
    }

    public String getCobertura() {
        return cobertura;
    }

    public String getAssistencia() {
        return assistencia;
    }

    public String getCoberturaAssistencia() {
        return coberturaAssistencia;
    }

    public String getVlrAssistencia() {
        return vlrAssistencia;
    }

    public String getComissao() {
        return comissao;
    }

    public String getAgenciamento() {
        return agenciamento;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public String getQtdParcelas() {
        return qtdParcelas;
    }

    public String getEmail() {
        return email;
    }

    public String getCelular() {
        return celular;
    }

    public String getCep() {
        return cep;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getNumero() {
        return numero;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }

    public String getDiaPagamento() {
        return diaPagamento;
    }

    public String getResultado_Esperado() {
        return Resultado_Esperado;
    }


    //SETS

    public void setCenario(String cenario) {
        this.cenario = cenario;
    }

    public void setCorretor(String corretor) {
        this.corretor = corretor;
    }

    public void setCnpj_Cpf(String cnpj_Cpf) {
        this.cnpj_Cpf = cnpj_Cpf;
    }

    public void setNomeSeguradoEstipulante(String nomeSeguradoEstipulante) {
        this.nomeSeguradoEstipulante = nomeSeguradoEstipulante;
    }

    public void setRamoAtividade(String ramoAtividade) {
        this.ramoAtividade = ramoAtividade;
    }

    public void setDataInicioVigencia(String dataInicioVigencia) {
        this.dataInicioVigencia = dataInicioVigencia;
    }

    public void setTipoSeguro(String tipoSeguro) {
        this.tipoSeguro = tipoSeguro;
    }

    public void setNmrVidasEmpregados(String nmrVidasEmpregados) {
        this.nmrVidasEmpregados = nmrVidasEmpregados;
    }

    public void setCapital_Individual_Empregados(String capital_Individual_Empregados) {
        this.capital_Individual_Empregados = capital_Individual_Empregados;
    }

    public void setNmrVidasSocios(String nmrVidasSocios) {
        this.nmrVidasSocios = nmrVidasSocios;
    }

    public void setCapital_Individual_Socios(String capital_Individual_Socios) {
        this.capital_Individual_Socios = capital_Individual_Socios;
    }

    public void setCobertura(String cobertura) {
        this.cobertura = cobertura;
    }

    public void setAssistencia(String assistencia) {
        this.assistencia = assistencia;
    }

    public void setCoberturaAssistencia(String coberturaAssistencia) {
        this.coberturaAssistencia = coberturaAssistencia;
    }

    public void setVlrAssistencia(String vlrAssistencia) {
        this.vlrAssistencia = vlrAssistencia;
    }

    public void setComissao(String comissao) {
        this.comissao = comissao;
    }

    public void setAgenciamento(String agenciamento) {
        this.agenciamento = agenciamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public void setQtdParcelas(String qtdParcelas) {
        this.qtdParcelas = qtdParcelas;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setDiaPagamento(String diaPagamento) {
        this.diaPagamento = diaPagamento;
    }

    public void setResultado_Esperado(String resultado_Esperado) {
        Resultado_Esperado = resultado_Esperado;
    }

    public void definirValor(String nome, String valor) {

        switch (nome) {
            case "cenario":
                setCenario(valor);
                break;
            case "corretor":
                setCorretor(valor);
                break;
            case "cnpj_Cpf":
                setCnpj_Cpf(valor);
                break;
            case "nomeSeguradoEstipulante":
                setNomeSeguradoEstipulante(valor);
                break;
            case "ramoAtividade":
                setRamoAtividade(valor);
                break;
            case "dataInicioVigencia":
                setDataInicioVigencia(valor);
                break;
            case "tipoSeguro":
                setTipoSeguro(valor);
                break;
            case "nmrVidasEmpregados":
                setNmrVidasEmpregados(valor);
                break;
            case "capital_Individual_Empregados":
                setCapital_Individual_Empregados(valor);
                break;
            case "nmrVidasSocios":
                setNmrVidasSocios(valor);
                break;
            case "capital_Individual_Socios":
                setCapital_Individual_Socios(valor);
                break;
            case "cobertura":
                setCobertura(valor);
                break;
            case "assistencia":
                setAssistencia(valor);
                break;
            case "coberturaAssistencia":
                setCoberturaAssistencia(valor);
                break;
            case "valorAssistencia":
                setVlrAssistencia(valor);
                break;
            case "comissao":
                setComissao(valor);
                break;
            case "agenciamento":
                setAgenciamento(valor);
                break;
            case "formaPagamento":
                setFormaPagamento(valor);
                break;
            case "qtdParcelas":
                setQtdParcelas(valor);
                break;
            case "email":
                setEmail(valor);
                break;
            case "celular":
                setCelular(valor);
                break;
            case "cep":
                setCep(valor);
                break;
            case "endereco":
                setEndereco(valor);
                break;
            case "numero":
                setNumero(valor);
                break;
            case "bairro":
                setBairro(valor);
                break;
            case "cidade":
                setCidade(valor);
                break;
            case "estado":
                setEstado(valor);
                break;
            case "diaPagamento":
                setDiaPagamento(valor);
                break;
            case "resultadoEsperado":
                setResultado_Esperado(valor);
        }
    }
}

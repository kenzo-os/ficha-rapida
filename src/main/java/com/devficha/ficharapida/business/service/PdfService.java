package com.devficha.ficharapida.business.service;

import com.devficha.ficharapida.business.entities.FichaAtendimento;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.layout.properties.VerticalAlignment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.io.IOException;

@Service
public class PdfService {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    private static final float FONT_SIZE_SMALL = 7f;
    private static final float FONT_SIZE_NORMAL = 8f;
    private static final float FONT_SIZE_MEDIUM = 9f;
    private static final float FONT_SIZE_TITLE = 11f;

    public byte[] gerarPdfFicha(FichaAtendimento ficha) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            PdfWriter writer = new PdfWriter(outputStream);
            PdfDocument pdfDocument = new PdfDocument(writer);
            pdfDocument.setDefaultPageSize(PageSize.A4);
            Document document = new Document(pdfDocument);
            document.setMargins(20, 20, 20, 20);

            // PÁGINA 1
            adicionarCabecalho(document, ficha);
            adicionarMotivoSolicitacao(document, ficha);
            adicionarClassificacaoRisco(document, ficha);
            adicionarDadosLocalOcorrencia(document, ficha);
            adicionarDadosVitima(document, ficha);
            adicionarDadosChamado(document, ficha);
            adicionarTipoAtendimento(document, ficha);
            adicionarSituacaoLocal(document, ficha);
            adicionarDescricaoCena(document, ficha);
            adicionarDadosClinicos(document, ficha);
            adicionarProcedimentosRealizados(document, ficha);
            adicionarCondutaMedicoRegulador(document, ficha);
            adicionarPertences(document, ficha);

            // PÁGINA 2
            document.add(new com.itextpdf.layout.element.AreaBreak());
            adicionarRelatorioEnfermagem(document, ficha);
            adicionarTermosResponsabilidade(document, ficha);
            adicionarCarimbos(document, ficha);

            document.close();
            return outputStream.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar PDF da ficha de atendimento", e);
        }
    }

    private void adicionarCabecalho(Document document, FichaAtendimento ficha) throws IOException {
        Table headerTable = new Table(UnitValue.createPercentArray(new float[]{25, 45, 30}))
                .useAllAvailableWidth();

        // Célula 1: Logo SAMU (à esquerda)
        Cell logoCell = new Cell();
        
        try {
            // Carregar a imagem do logo
            ClassPathResource imgResource = new ClassPathResource("static/images/samu-192_logo.png");
            byte[] imageBytes = imgResource.getInputStream().readAllBytes();
            Image logo = new Image(ImageDataFactory.create(imageBytes));
            
            // Ajustar o tamanho da imagem
            logo.setWidth(139);
            logo.setHeight(80);
            
            logoCell.add(logo);
        } catch (IOException e) {
            // Se não conseguir carregar a imagem, usa apenas texto
            logoCell.add(new Paragraph("LOGO")
                    .setFontSize(FONT_SIZE_TITLE)
                    .setBold());
        }
        
        logoCell.setBorder(Border.NO_BORDER)
                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                .setTextAlignment(TextAlignment.CENTER);

        // Célula 2: Textos centrais (SAMU 192, FICHA DE ATENDIMENTO, USB)
        Cell titleCell = new Cell()
                .add(new Paragraph("SAMU 192")
                        .setFontSize(FONT_SIZE_TITLE)
                        .setBold()
                        .setTextAlignment(TextAlignment.CENTER))
                .add(new Paragraph("FICHA DE ATENDIMENTO")
                        .setFontSize(FONT_SIZE_MEDIUM)
                        .setBold()
                        .setTextAlignment(TextAlignment.CENTER))
                .add(new Paragraph("USB")
                        .setFontSize(FONT_SIZE_NORMAL)
                        .setTextAlignment(TextAlignment.CENTER))
                .setBorder(Border.NO_BORDER)
                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                .setTextAlignment(TextAlignment.CENTER);

        // Célula 3: DATA, KM INICIAL e KM FINAL (à direita, empilhados)
        String dataFormatada = ficha.getDataAtendimento() != null
                ? ficha.getDataAtendimento().format(DATE_FORMATTER)
                : "";
        
        Table rightTable = new Table(1).useAllAvailableWidth();
        rightTable.addCell(createCell("DATA", dataFormatada, true));
        rightTable.addCell(createCell("KM INICIAL", ficha.getKmInicial(), true));
        rightTable.addCell(createCell("KM FINAL", ficha.getKmFinal(), true));
        
        Cell rightCell = new Cell()
                .add(rightTable)
                .setBorder(Border.NO_BORDER)
                .setVerticalAlignment(VerticalAlignment.MIDDLE);

        // Adicionar as três células à tabela do cabeçalho
        headerTable.addCell(logoCell);
        headerTable.addCell(titleCell);
        headerTable.addCell(rightCell);

        document.add(headerTable);
    }

    private void adicionarMotivoSolicitacao(Document document, FichaAtendimento ficha) {
        Table table = new Table(1).useAllAvailableWidth().setMarginTop(5);
        table.addCell(createHeaderCell("MOTIVO DA SOLICITAÇÃO:"));
        table.addCell(createContentCell(ficha.getMotivoSolicitacao()));
        document.add(table);
    }

    private void adicionarClassificacaoRisco(Document document, FichaAtendimento ficha) {
        Table table = new Table(UnitValue.createPercentArray(new float[]{30, 17.5f, 17.5f, 17.5f, 17.5f}))
                .useAllAvailableWidth()
                .setMarginTop(5);

        table.addCell(createHeaderCell("CLASSIFICAÇÃO DE RISCO"));
        table.addCell(createCheckboxCell("VERMELHA", ficha.isVermelha()));
        table.addCell(createCheckboxCell("AMARELA", ficha.isAmarela()));
        table.addCell(createCheckboxCell("VERDE", ficha.isVerde()));
        table.addCell(createCheckboxCell("AZUL", ficha.isAzul()));

        document.add(table);
    }

    private void adicionarDadosLocalOcorrencia(Document document, FichaAtendimento ficha) {
        Table table = new Table(1).useAllAvailableWidth().setMarginTop(5);
        table.addCell(createHeaderCell("LOCAL OCORRÊNCIA"));

        Table innerTable = new Table(UnitValue.createPercentArray(new float[]{15, 15, 15, 15, 10, 30}))
                .useAllAvailableWidth();
        innerTable.addCell(createCheckboxCell("RESIDÊNCIA", ficha.isResidencia()));
        innerTable.addCell(createCheckboxCell("VIA PÚBLICA", ficha.isViaPublica()));
        innerTable.addCell(createCheckboxCell("RODOVIA", ficha.isRodovia()));
        innerTable.addCell(createCheckboxCell("PS", ficha.isPs()));
        innerTable.addCell(createCheckboxCell("UBS", ficha.isUbs()));
        innerTable.addCell(createCell("OUTROS:", ficha.getLocalOcorrenciaOutros(), false));

        table.addCell(new Cell().add(innerTable).setBorder(new SolidBorder(1)));

        table.addCell(createCell("ENDEREÇO:", ficha.getEnderecoOcorrencia(), false));
        
        Table endTable = new Table(UnitValue.createPercentArray(new float[]{20, 40, 40})).useAllAvailableWidth();
        endTable.addCell(createCell("NÚMERO:", 
                ficha.getNumeroEnderecoOcorrencia() != null ? ficha.getNumeroEnderecoOcorrencia().toString() : "", false));
        endTable.addCell(createCell("REFERÊNCIA:", ficha.getReferenciaEnderecoOcorrencia(), false));
        endTable.addCell(createCell("CONTATO:", ficha.getContatoEnderecoOcorrencia(), false));
        
        table.addCell(new Cell().add(endTable).setBorder(new SolidBorder(1)));

        document.add(table);
    }

    private void adicionarDadosVitima(Document document, FichaAtendimento ficha) {
        Table table = new Table(1).useAllAvailableWidth().setMarginTop(5);
        table.addCell(createHeaderCell("DADOS DA VÍTIMA:"));

        Table row1 = new Table(UnitValue.createPercentArray(new float[]{50, 15, 35})).useAllAvailableWidth();
        row1.addCell(createCell("NOME", ficha.getNomeVitima(), false));
        row1.addCell(createCell("IDADE:", 
                ficha.getIdadeVitima() != null ? ficha.getIdadeVitima().toString() : "", false));
        row1.addCell(createCell("DN:", 
                ficha.getDataNascimentoVitima() != null ? ficha.getDataNascimentoVitima().format(DATE_FORMATTER) : "", false));
        table.addCell(new Cell().add(row1).setBorder(new SolidBorder(1)));

        Table row2 = new Table(UnitValue.createPercentArray(new float[]{50, 20, 30})).useAllAvailableWidth();
        row2.addCell(createCell("ENDEREÇO:", ficha.getEnderecoVitima(), false));
        row2.addCell(createCell("NÚMERO:", ficha.getNumeroEnderecoVitima(), false));
        row2.addCell(createCell("BAIRRO:", ficha.getBairroEnderecoVitima(), false));
        table.addCell(new Cell().add(row2).setBorder(new SolidBorder(1)));

        table.addCell(createCell("PAI:", ficha.getNomePaiVitima(), false));
        table.addCell(createCell("MÃE:", ficha.getNomeMaeVitima(), false));

        Table row3 = new Table(UnitValue.createPercentArray(new float[]{30, 30, 40})).useAllAvailableWidth();
        row3.addCell(createCell("CNS:", ficha.getCnsVitima(), false));
        row3.addCell(createCell("RG:", ficha.getRgVitima(), false));
        row3.addCell(createCell("CPF:", ficha.getCpfVitima(), false));
        table.addCell(new Cell().add(row3).setBorder(new SolidBorder(1)));

        document.add(table);
    }

    private void adicionarDadosChamado(Document document, FichaAtendimento ficha) {
        Table table = new Table(8).useAllAvailableWidth().setMarginTop(5);

        table.addCell(createSmallCell("HORA CHAMADO", formatTime(ficha.getHoraChamado())));
        table.addCell(createSmallCell("HORA TRANSMISSÃO", formatTime(ficha.getHoraTransmissao())));
        table.addCell(createSmallCell("HORA SAÍDA", formatTime(ficha.getHoraSaida())));
        table.addCell(createSmallCell("H CHEGADA LOCAL", formatTime(ficha.getHoraChegadaLocal())));
        table.addCell(createSmallCell("H SAÍDA LOCAL", formatTime(ficha.getHoraSaidaLocal())));
        table.addCell(createSmallCell("H CHEGADA HOSPITAL", formatTime(ficha.getHoraChegadaHospital())));
        table.addCell(createSmallCell("H SAÍDA HOSPITAL", formatTime(ficha.getHoraSaidaHospital())));
        table.addCell(createSmallCell("H RETORNO À BASE", formatTime(ficha.getHoraRetornoBase())));

        document.add(table);
    }

    private void adicionarTipoAtendimento(Document document, FichaAtendimento ficha) {
        Table table = new Table(1).useAllAvailableWidth().setMarginTop(5);
        table.addCell(createHeaderCell("TIPO ATENDIMENTO:"));

        Table row1 = new Table(5).useAllAvailableWidth();
        row1.addCell(createCheckboxCell("ATROPELAMENTO", ficha.isAtropelamento()));
        row1.addCell(createCheckboxCell("SUSPEITA IAM", ficha.isSuspeitaIam()));
        row1.addCell(createCheckboxCell("QUEIMADURAS", ficha.isQueimaduras()));
        row1.addCell(createCheckboxCell("PSIQUIÁTRICO", ficha.isPsiquiatrico()));
        row1.addCell(createCheckboxCell("TRANSFERÊNCIA", ficha.isTransferencia()));

        Table row2 = new Table(5).useAllAvailableWidth();
        row2.addCell(createCheckboxCell("ACIDENTE TRÂNSITO", ficha.isAcidenteTransito()));
        row2.addCell(createCheckboxCell("SUSPEITA AVC", ficha.isSuspeitaAvc()));
        row2.addCell(createCheckboxCell("INTOXICAÇÃO", ficha.isIntoxicacao()));
        row2.addCell(createCheckboxCell("HIPO/HIPERTENSÃO", ficha.isHipoHipertensao()));
        row2.addCell(createCheckboxCell("OBSTETRÍCIA", ficha.isObstetricia()));

        Table row3 = new Table(5).useAllAvailableWidth();
        row3.addCell(createCheckboxCell("AGRESSÃO", ficha.isAgressao()));
        row3.addCell(createCheckboxCell("QUEDA", ficha.isQueda()));
        row3.addCell(createCheckboxCell("HIPO/HIPER GLICEMIA", ficha.isHipoHiperglicemia()));
        row3.addCell(createCheckboxCell("ALCOOLIZADO", ficha.isAlcoolizado()));
        row3.addCell(createCell("OUTROS:", ficha.getOutrosTipoAtendimento(), false));

        table.addCell(new Cell().add(row1).setBorder(new SolidBorder(1)));
        table.addCell(new Cell().add(row2).setBorder(new SolidBorder(1)));
        table.addCell(new Cell().add(row3).setBorder(new SolidBorder(1)));

        document.add(table);
    }

    private void adicionarSituacaoLocal(Document document, FichaAtendimento ficha) {
        Table table = new Table(UnitValue.createPercentArray(new float[]{20, 16, 16, 16, 16, 16}))
                .useAllAvailableWidth()
                .setMarginTop(5);

        table.addCell(createHeaderCell("SITUAÇÃO LOCAL"));
        table.addCell(createCheckboxCell("MORTE ÓBVIA", ficha.isMorteObvia()));
        table.addCell(createCheckboxCell("CHAMADO FALSO", ficha.isChamadoFalso()));
        table.addCell(createCheckboxCell("EVADIU-SE", ficha.isEvadiu()));
        table.addCell(createCheckboxCell("QTA", ficha.isQta()));
        table.addCell(createCell("OUTROS:", ficha.getOutrosSituacaoLocal(), false));

        document.add(table);
    }

    private void adicionarDescricaoCena(Document document, FichaAtendimento ficha) {
        Table table = new Table(1).useAllAvailableWidth().setMarginTop(5);
        table.addCell(createHeaderCell("DESCRIÇÃO DA CENA:"));
        
        // Criar uma tabela com 2 colunas: texto à esquerda e imagem à direita
        Table contentTable = new Table(UnitValue.createPercentArray(new float[]{60, 40}))
                .useAllAvailableWidth();
        
        // Célula com o texto da descrição
        Cell textCell = new Cell()
                .add(new Paragraph(ficha.getDescricaoCena() != null ? ficha.getDescricaoCena() : "")
                        .setFontSize(FONT_SIZE_NORMAL))
                .setMinHeight(120)
                .setBorder(Border.NO_BORDER)
                .setPadding(3);
        
        // Célula com a imagem dos contornos do corpo
        Cell imageCell = new Cell()
                .setBorder(Border.NO_BORDER)
                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                .setTextAlignment(TextAlignment.CENTER);
        
        try {
            ClassPathResource imgResource = new ClassPathResource("static/images/image_outlinehbody.png");
            byte[] imageBytes = imgResource.getInputStream().readAllBytes();
            Image bodyImage = new Image(ImageDataFactory.create(imageBytes));
            
            // Ajustar o tamanho da imagem para caber na célula
            bodyImage.setWidth(140);
            bodyImage.setHeight(110);
            bodyImage.setHorizontalAlignment(HorizontalAlignment.CENTER);
            
            imageCell.add(bodyImage);
        } catch (IOException e) {
            // Se não conseguir carregar a imagem, deixa a célula vazia
            imageCell.add(new Paragraph("").setFontSize(FONT_SIZE_SMALL));
        }
        
        contentTable.addCell(textCell);
        contentTable.addCell(imageCell);
        
        Cell contentCell = new Cell()
                .add(contentTable)
                .setBorder(new SolidBorder(1))
                .setPadding(0);
        
        table.addCell(contentCell);
        document.add(table);
    }

    private void adicionarDadosClinicos(Document document, FichaAtendimento ficha) {
        // Avaliação Primária
        Table avalTable = new Table(UnitValue.createPercentArray(new float[]{30, 17.5f, 17.5f, 17.5f, 17.5f}))
                .useAllAvailableWidth()
                .setMarginTop(5);

        avalTable.addCell(createHeaderCell("AVALIAÇÃO PRIMÁRIA"));
        avalTable.addCell(createCheckboxCell("VIA AÉREA LIBERADA", ficha.isViaAereaLiberada()));
        avalTable.addCell(createCheckboxCell("RESPIRAÇÃO", ficha.isRespiracao()));
        avalTable.addCell(createCheckboxCell("CIRCULAÇÃO", ficha.isCirculacao()));
        avalTable.addCell(createCheckboxCell("CONSCIENTE", ficha.isConsciente()));

        document.add(avalTable);

        // Pupilas
        Table pupilaTable = new Table(UnitValue.createPercentArray(new float[]{15, 10, 10, 10, 10, 10, 10, 10, 10, 5}))
                .useAllAvailableWidth()
                .setMarginTop(3);

        pupilaTable.addCell(createHeaderCell("PUPILAS"));
        pupilaTable.addCell(createSmallHeaderCell("DIREITA"));
        pupilaTable.addCell(createPupilaCell(ficha.isDireitaMidriase()));
        pupilaTable.addCell(createPupilaCell(ficha.isDireitaIsocoria()));
        pupilaTable.addCell(createPupilaCell(ficha.isDireitaMiose()));
        pupilaTable.addCell(createSmallHeaderCell("ESQUERDA"));
        pupilaTable.addCell(createPupilaCell(ficha.isEsquerdaMidriase()));
        pupilaTable.addCell(createPupilaCell(ficha.isEsquerdaIsocoria()));
        pupilaTable.addCell(createPupilaCell(ficha.isEsquerdaMiose()));
        pupilaTable.addCell(createSmallCell("REATIVAS", 
                (ficha.isDireitaReativa() ? "D " : "") + (ficha.isEsquerdaReativa() ? "E" : "")));

        document.add(pupilaTable);

        // Sinais Vitais
        Table sinaisTable = new Table(7).useAllAvailableWidth().setMarginTop(3);
        sinaisTable.addCell(createHeaderCell("SINAIS VITAIS"));
        sinaisTable.addCell(createSmallCell("PA", formatValue(ficha.getPressaoSistolica()) + "/" + formatValue(ficha.getPressaoDiastolica())));
        sinaisTable.addCell(createSmallCell("FC", formatValue(ficha.getFrequenciaCardiaca())));
        sinaisTable.addCell(createSmallCell("FR", formatValue(ficha.getFrequenciaRespiratoria())));
        sinaisTable.addCell(createSmallCell("SAT", formatValue(ficha.getSaturacao())));
        sinaisTable.addCell(createSmallCell("DEXTRO", formatValue(ficha.getDextro())));
        sinaisTable.addCell(createSmallCell("TEMPERATURA", formatValue(ficha.getTemperatura())));

        document.add(sinaisTable);

        // Pele
        Table peleTable = new Table(7).useAllAvailableWidth().setMarginTop(3);
        peleTable.addCell(createHeaderCell("PELE"));
        peleTable.addCell(createCheckboxCell("CORADA", ficha.isCorada()));
        peleTable.addCell(createCheckboxCell("CIANÓTICA", ficha.isCianotica()));
        peleTable.addCell(createCheckboxCell("QUENTE", ficha.isQuente()));
        peleTable.addCell(createCheckboxCell("FRIA", ficha.isFria()));
        peleTable.addCell(createCheckboxCell("ICTÉRICA", ficha.isIcterica()));
        peleTable.addCell(createCheckboxCell("SUDOREICA", ficha.isSudoreica()));

        document.add(peleTable);

        // Antecedentes Patológicos
        Table anteceTable = new Table(8).useAllAvailableWidth().setMarginTop(3);
        anteceTable.addCell(createHeaderCell("ANTECEDENTES PATOLÓGICOS"));
        anteceTable.addCell(createCheckboxCell("IAM", ficha.isIam()));
        anteceTable.addCell(createCheckboxCell("AVC", ficha.isAvc()));
        anteceTable.addCell(createCheckboxCell("DIABETES", ficha.isDiabetes()));
        anteceTable.addCell(createCheckboxCell("ASMA - BRONQUITE", ficha.isAsmaBronquite()));
        anteceTable.addCell(createCheckboxCell("CONVULSÃO", ficha.isConvulsao()));
        anteceTable.addCell(createCheckboxCell("HAS", ficha.isHas()));
        anteceTable.addCell(createCheckboxCell("CA", ficha.isCa()));

        document.add(anteceTable);

        // Medicamentos e Alergias
        Table medTable = new Table(1).useAllAvailableWidth().setMarginTop(3);
        medTable.addCell(createCell("MEDICAMENTO EM USO", ficha.getMedicamentosEmUso(), false));
        medTable.addCell(createCell("ALERGIAS", ficha.getAlergias(), false));

        document.add(medTable);
    }

    private void adicionarProcedimentosRealizados(Document document, FichaAtendimento ficha) {
        Table table = new Table(1).useAllAvailableWidth().setMarginTop(5);
        table.addCell(createHeaderCell("PROCEDIMENTOS REALIZADOS:"));

        Table row1 = new Table(5).useAllAvailableWidth();
        row1.addCell(createCheckboxCell("CÂNULA GUEDEL", ficha.isCanulaGuedel()));
        row1.addCell(createCheckboxCell("OXIGÊNIO", ficha.isOxigenio()));
        row1.addCell(createCheckboxCell("OXIMETRIA", ficha.isOximetria()));
        row1.addCell(createCheckboxCell("ACESSO VENOSO", ficha.isAcessoVenoso()));
        row1.addCell(createCheckboxCell("RCP", ficha.isRcp()));

        Table row2 = new Table(5).useAllAvailableWidth();
        row2.addCell(createCheckboxCell("DEA", ficha.isDea()));
        row2.addCell(createCheckboxCell("COLAR CERVICAL", ficha.isColarCervical()));
        row2.addCell(createCheckboxCell("PRANCHA", ficha.isPrancha()));
        row2.addCell(createCheckboxCell("KED", ficha.isKed()));
        row2.addCell(createCheckboxCell("TALAS", ficha.isTalas()));

        Table row3 = new Table(5).useAllAvailableWidth();
        row3.addCell(createCheckboxCell("CONTENSÃO PSQ", ficha.isContensaoPsq()));
        row3.addCell(createCheckboxCell("KIT PARTO", ficha.isKitParto()));
        row3.addCell(createCheckboxCell("CURATIVO", ficha.isCurativo()));
        row3.addCell(createCheckboxCell("APOIO USA", ficha.isApoioUsa()));
        row3.addCell(createCell("OUTROS:", ficha.getProcedimentosRealizadosOutros(), false));

        table.addCell(new Cell().add(row1).setBorder(new SolidBorder(1)));
        table.addCell(new Cell().add(row2).setBorder(new SolidBorder(1)));
        table.addCell(new Cell().add(row3).setBorder(new SolidBorder(1)));

        document.add(table);
    }

    private void adicionarCondutaMedicoRegulador(Document document, FichaAtendimento ficha) {
        Table table = new Table(1).useAllAvailableWidth().setMarginTop(5);
        table.addCell(createCell("CONDUTA:", ficha.getConduta(), false));
        table.addCell(createCell("MÉDICO REGULADOR:", ficha.getMedicoRegulador(), false));
        document.add(table);
    }

    private void adicionarPertences(Document document, FichaAtendimento ficha) {
        Table table = new Table(1).useAllAvailableWidth().setMarginTop(5);
        table.addCell(createHeaderCell("DESCRIÇÃO DOS PERTENCES DO PACIENTE (DEIXADOS NO DESTINO):"));
        
        Cell contentCell = new Cell()
                .add(new Paragraph(ficha.getPertences() != null ? ficha.getPertences() : "")
                        .setFontSize(FONT_SIZE_NORMAL))
                .setMinHeight(30)
                .setBorder(new SolidBorder(1));
        
        table.addCell(contentCell);
        document.add(table);
    }

    private void adicionarRelatorioEnfermagem(Document document, FichaAtendimento ficha) {
        Table table = new Table(1).useAllAvailableWidth();
        table.addCell(createHeaderCell("RELATÓRIO DE ENFERMAGEM:"));
        
        // Criar 20 linhas para escrita
        String relatorio = ficha.getRelatorioEnfermagem() != null ? ficha.getRelatorioEnfermagem() : "";
        
        Cell contentCell = new Cell()
                .add(new Paragraph(relatorio).setFontSize(FONT_SIZE_NORMAL))
                .setMinHeight(350)
                .setBorder(new SolidBorder(1));
        
        table.addCell(contentCell);
        document.add(table);
    }

    private void adicionarTermosResponsabilidade(Document document, FichaAtendimento ficha) {
        Table table = new Table(1).useAllAvailableWidth().setMarginTop(10);

        // Recusa de Atendimento
        Cell recusaCell = new Cell()
                .add(new Paragraph()
                        .add(createCheckbox(ficha.isRecusaAtendimento()))
                        .add(" RECUSA DE ATENDIMENTO: ")
                        .setFontSize(FONT_SIZE_NORMAL)
                        .setBold())
                .add(new Paragraph("Eu abaixo assinado, devidamente orientado e ciente dos riscos, recuso atendimento, " +
                        "assumindo total responsabilidade de tal recusa, isentando o serviço do SAMU de qualquer " +
                        "responsabilidade advinda deste ato.")
                        .setFontSize(FONT_SIZE_SMALL))
                .setBorder(new SolidBorder(1))
                .setPadding(5);

        // Confirmação de Comparecimento
        Cell comparecimentoCell = new Cell()
                .add(new Paragraph()
                        .add(createCheckbox(ficha.isConfirmacaoComparecimento()))
                        .add(" CONFIRMAÇÃO DE COMPARECIMENTO DA EQUIPE: ")
                        .setFontSize(FONT_SIZE_NORMAL)
                        .setBold())
                .add(new Paragraph("Eu abaixo assinado, confirmo o comparecimento da equipe do SAMU no local da " +
                        "ocorrência e a ausência de vítima (s) no mesmo, isentando o serviço do SAMU de qualquer " +
                        "responsabilidade advinda após a sua saída do local.")
                        .setFontSize(FONT_SIZE_SMALL))
                .setBorder(new SolidBorder(1))
                .setPadding(5)
                .setMarginTop(5);

        // Alta Local
        Cell altaCell = new Cell()
                .add(new Paragraph()
                        .add(createCheckbox(ficha.isAltaLocal()))
                        .add(" ALTA LOCAL: ")
                        .setFontSize(FONT_SIZE_NORMAL)
                        .setBold())
                .add(new Paragraph("Eu abaixo assinado, confirmo o comparecimento da equipe do SAMU no local da " +
                        "ocorrência no qual fui atendido e devidamente orientado isentando o serviço do SAMU de qualquer " +
                        "responsabilidade advinda após a sua saída do local.")
                        .setFontSize(FONT_SIZE_SMALL))
                .setBorder(new SolidBorder(1))
                .setPadding(5)
                .setMarginTop(5);

        table.addCell(recusaCell);
        table.addCell(comparecimentoCell);
        table.addCell(altaCell);

        // Assinatura e RG
        Table assinaturaTable = new Table(2).useAllAvailableWidth().setMarginTop(10);
        assinaturaTable.addCell(createCell("ASSINATURA:", ficha.getAssinaturaResponsavel(), false));
        assinaturaTable.addCell(createCell("RG:", ficha.getRgResponsavel(), false));

        table.addCell(new Cell().add(assinaturaTable).setBorder(Border.NO_BORDER));

        document.add(table);
    }

    private void adicionarCarimbos(Document document, FichaAtendimento ficha) {
        Table table = new Table(4).useAllAvailableWidth().setMarginTop(15);

        table.addCell(createCarimboCell("DESTINO", ficha.getCarimboDestino()));
        table.addCell(createCarimboCell("RECEBIDO POR (CARIMBO)", ficha.getCarimboRecebidoPor()));
        table.addCell(createCarimboCell("TÉCNICO ENFERMAGEM (SAMU)", ficha.getCarimboTecnicoEnfermagem()));
        table.addCell(createCarimboCell("SOCORRISTA (SAMU)", 
                ficha.getSocorrista() != null ? ficha.getSocorrista().getCarimbo() : ""));

        document.add(table);
    }

    // Métodos auxiliares para criar células
    private Cell createHeaderCell(String text) {
        return new Cell()
                .add(new Paragraph(text).setFontSize(FONT_SIZE_NORMAL).setBold())
                .setBackgroundColor(new DeviceRgb(220, 220, 220))
                .setBorder(new SolidBorder(1))
                .setPadding(3)
                .setTextAlignment(TextAlignment.LEFT);
    }

    private Cell createSmallHeaderCell(String text) {
        return new Cell()
                .add(new Paragraph(text).setFontSize(FONT_SIZE_SMALL).setBold())
                .setBackgroundColor(new DeviceRgb(240, 240, 240))
                .setBorder(new SolidBorder(1))
                .setPadding(2)
                .setTextAlignment(TextAlignment.CENTER);
    }

    private Cell createCell(String label, String value, boolean withBorder) {
        Paragraph p = new Paragraph()
                .add(new com.itextpdf.layout.element.Text(label + " ").setFontSize(FONT_SIZE_SMALL).setBold())
                .add(new com.itextpdf.layout.element.Text(value != null ? value : "").setFontSize(FONT_SIZE_NORMAL));

        Cell cell = new Cell().add(p).setPadding(3);
        if (withBorder) {
            cell.setBorder(new SolidBorder(1));
        } else {
            cell.setBorder(new SolidBorder(1));
        }
        return cell;
    }

    private Cell createSmallCell(String label, String value) {
        Paragraph p = new Paragraph()
                .add(new com.itextpdf.layout.element.Text(label).setFontSize(FONT_SIZE_SMALL).setBold())
                .add(new com.itextpdf.layout.element.Text("\n" + (value != null ? value : "")).setFontSize(FONT_SIZE_SMALL));

        return new Cell()
                .add(p)
                .setBorder(new SolidBorder(1))
                .setPadding(2)
                .setTextAlignment(TextAlignment.CENTER);
    }

    private Cell createCheckboxCell(String label, boolean checked) {
        Paragraph p = new Paragraph()
                .add(createCheckbox(checked))
                .add(" " + label)
                .setFontSize(FONT_SIZE_SMALL);

        return new Cell()
                .add(p)
                .setBorder(new SolidBorder(1))
                .setPadding(3);
    }

    private Cell createContentCell(String content) {
        return new Cell()
                .add(new Paragraph(content != null ? content : "").setFontSize(FONT_SIZE_NORMAL))
                .setBorder(new SolidBorder(1))
                .setPadding(3)
                .setMinHeight(20);
    }

    private Cell createPupilaCell(boolean marked) {
        return new Cell()
                .add(new Paragraph(marked ? "●" : "○").setFontSize(FONT_SIZE_MEDIUM))
                .setBorder(new SolidBorder(1))
                .setPadding(2)
                .setTextAlignment(TextAlignment.CENTER);
    }

    private Cell createCarimboCell(String label, String content) {
        return new Cell()
                .add(new Paragraph(label).setFontSize(FONT_SIZE_SMALL).setBold())
                .add(new Paragraph(content != null ? content : "").setFontSize(FONT_SIZE_SMALL))
                .setBorder(new SolidBorder(1))
                .setPadding(5)
                .setMinHeight(60)
                .setTextAlignment(TextAlignment.CENTER);
    }

    private String createCheckbox(boolean checked) {
        return checked ? "☑" : "☐";
    }

    private String formatTime(LocalTime time) {
        return time != null ? time.format(TIME_FORMATTER) : "";
    }

    private String formatValue(Integer value) {
        return value != null ? value.toString() : "";
    }
}

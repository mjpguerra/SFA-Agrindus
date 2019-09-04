package sfa.android;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.frame.mobilefast.DefaultListModel;
import com.mobilefast.assinatura.GUIAssinatura;
import com.mobilefast.midia.FotoCamera;
import com.mobilefast.midia.GaleriaImagem;
import com.mobilefast.midia.GridImagens;
import com.mobilefast.midia.TouchImagem;
import com.mobilefast.sfandroid.exception.SFAndroidException;
import com.mobilefast.sfandroid.gui.GUICadastroCliente;
import com.mobilefast.sfandroid.gui.GUIDetalheCliente;
import com.mobilefast.sfandroid.gui.GUIFastSync;
import com.mobilefast.sfandroid.gui.GUIGrupoProdutoPrincipal;
import com.mobilefast.sfandroid.gui.GUIGrupoProdutos;
import com.mobilefast.sfandroid.gui.GUIListaCliente;
import com.mobilefast.sfandroid.gui.GUIListaClienteCadastrado;
import com.mobilefast.sfandroid.gui.GUIListaProdutoPrincipal;
import com.mobilefast.sfandroid.gui.GUIListaStatusPedido;
import com.mobilefast.sfandroid.gui.GUILogin;
import com.mobilefast.sfandroid.gui.GUIMovimentoPedido;
import com.mobilefast.sfandroid.gui.GUIMsgLog;
import com.mobilefast.sfandroid.gui.GUIPedidoCarrinho;
import com.mobilefast.sfandroid.gui.GUIPrincipal;
import com.mobilefast.sfandroid.gui.GUIPrincipalFastSync;
import com.mobilefast.sfandroid.gui.GUIProdutosPedido;
import com.mobilefast.sfandroid.gui.GUIRotasCliente;
import com.mobilefast.sfandroid.gui.GUITabInfoCliente;
import com.mobilefast.sfandroid.gui.GUITabPedido;
import com.mobilefast.sfandroid.model.FastSync;
import com.mobilefast.sfandroid.model.FonteEquipamento;
import com.mobilefast.sfandroid.model.LocatorDAO;
import com.mobilefast.sfandroid.model.MBCDCL;
import com.mobilefast.sfandroid.model.MBCL;
import com.mobilefast.sfandroid.model.MBCP;
import com.mobilefast.sfandroid.model.MBDE;
import com.mobilefast.sfandroid.model.MBGR;
import com.mobilefast.sfandroid.model.MBLP;
import com.mobilefast.sfandroid.model.MBPD;
import com.mobilefast.sfandroid.model.MBPM;
import com.mobilefast.sfandroid.model.MBPR;
import com.mobilefast.sfandroid.model.MBPRDAO;
import com.mobilefast.sfandroid.model.MBTM;
import com.mobilefast.sfandroid.model.MBTP;
import com.mobilefast.sfandroid.model.MBVN;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class CoreSFAndroid {
    public static final String TAG = "";
    public static MBVN VENDEDOR_CORRENTE = null;
    public static FastSync FASTSYNC_CORRENTE = null;
    public static MBGR GRUPO_CORRENTE = null;
    public static MBPD ITENS_CORRENTE = null;
    public static MBPM PEDIDO_CORRENTE = null;
    public static MBCDCL CADASTRO = null;
    public static FonteEquipamento FONTE_CORRENTE = null;
    public static List<MBCL> lCli = null;
    public static Map<String, MBPR> mProdutos = new HashMap<String, MBPR>();
    public static Map<String, MBCL> mCliente = new HashMap<String, MBCL>();
    public static ArrayList<DefaultListModel> lista_prod = new ArrayList<DefaultListModel>();
    public static ArrayList<DefaultListModel> lista_prod2 = new ArrayList<DefaultListModel>();
    public static ArrayList<DefaultListModel> lista_prodGrupo = new ArrayList<DefaultListModel>();
    public static ArrayList<DefaultListModel> lista_grupo = new ArrayList<DefaultListModel>();
    public static ArrayList<DefaultListModel> lista_itensPedido = new ArrayList<DefaultListModel>();
    public static ArrayList<DefaultListModel> lista_pedidos = new ArrayList<DefaultListModel>();
    public static ArrayList<DefaultListModel> lista_cliente = new ArrayList<DefaultListModel>();
    public static ArrayList<DefaultListModel> lista_roteiro = new ArrayList<DefaultListModel>();
    public static String CONDPAGTO = "";
    public static String TPGTO = "";
    public static String TCLIENTE = "";
    public static DefaultListModel dro = new DefaultListModel();
    public static DefaultListModel dcl = new DefaultListModel();
    public static DefaultListModel dd = new DefaultListModel();
    public static DefaultListModel di = new DefaultListModel();
    public static DefaultListModel dg = new DefaultListModel();
    public static DefaultListModel ps = new DefaultListModel();
    public static String Acomp;
    public static String Cond_Pagto;
    public static String DataEntrega;
    public static String cod_cliente;
    public static String codigo_cotacao;
    public static int num_ped;
    public static int codigo_pedidoc = 0;
    public static String codigo_vendedor;
    public static int controler_consumo = 0;
    public static int controler_pedido = 0;
    public static int controler_versao;
    public static int desc_contratual = 0;
    public static int desc_condicao = 0;
    public static String PEDINI;
    public static String PROXPED;
    public static String TPagto;
    public static short empresa;
    public static int filial;
    public static int flag_grupo = 0;
    public static int flag_rota = 0;
    public static String nome_cliente;
    public static String tipo_cliente;
    public static String preco_lista;
    public static int nm_lista;
    public static int nm_listaVend;
    public static int tamanho_fonte;
    public static int total_itens;
    public static double total_ped;
    public static double total_contratual;
    public static double total_comissao;
    public static int total_peso;
    public static double desc_trab;
    public static double mk_itens;
    public static double custo;
    public static double venda;
    public static List lTotal = new ArrayList();
    public static Map<String, MBPR> mCarrinho = new HashMap<String, MBPR>();
    public static Map<String, MBPD> mItens = new HashMap<String, MBPD>();
    static File sdcard = Environment.getExternalStorageDirectory();
    public final static File NOME_DIRETORIO_ASS = new File(sdcard.getAbsolutePath() + "/SFAndroid/Assinatura");
    private static CoreSFAndroid intance = new CoreSFAndroid();
    private static Handler handler = new Handler();
    private List<MBPR> lP = null;
    private List<MBPM> lPed = null;
    private List<MBPD> lItens = null;
    private List<MBTP> ltp = null;

    private CoreSFAndroid() {
    }

    public static void gerar_total_itens() {
        total_itens = 0;
        total_ped = 0.0D;
        desc_trab = 0.0D;
        total_contratual = 0.0D;
        total_comissao = 0.0D;
        lTotal.clear();
        if (CoreSFAndroid.lista_itensPedido.size() > 0) {
            for (int var1 = 0; var1 < CoreSFAndroid.lista_itensPedido.size(); ++var1) {
                CoreSFAndroid.di = CoreSFAndroid.lista_itensPedido.get(var1);
                total_peso += CoreSFAndroid.di.getPeso_item() * CoreSFAndroid.di.getQuantidade();
                total_ped += CoreSFAndroid.di.getTotalvalor();
                total_comissao += CoreSFAndroid.di.getComissao() * CoreSFAndroid.di.getQuantidade();
                total_contratual += CoreSFAndroid.di.getcontratual() * CoreSFAndroid.di.getQuantidade();
                desc_trab += CoreSFAndroid.di.getdescTrab() * CoreSFAndroid.di.getQuantidade();
                ++total_itens;
            }
        }

        //total_contratual = total_ped * (desc_contratual / 1000);

        if (GUIMovimentoPedido.btvalor == null) {
            GUIMovimentoPedido.btvalor = new Button(ControllerSFAndroid.getInstancia().getContext());
            GUIMovimentoPedido.btvalor.setText("Brt: " + CoreSFAndroid.formataMoeda(total_ped) + "  Des: " + CoreSFAndroid.formataMoeda(desc_trab) + "  DesC: " + CoreSFAndroid.formataMoeda(desc_contratual) + "  Liq: " + CoreSFAndroid.formataMoeda(total_ped - desc_trab - total_contratual) + "  Itens: " + String.valueOf(total_itens) + " Comissão: " + CoreSFAndroid.formataMoeda(total_comissao));
        } else {
            GUIMovimentoPedido.btvalor.setText("Brt: " + CoreSFAndroid.formataMoeda(total_ped) + "  Des: " + CoreSFAndroid.formataMoeda(desc_trab) + "  DesC: " + CoreSFAndroid.formataMoeda(desc_contratual) + "  Liq: " + CoreSFAndroid.formataMoeda(total_ped - desc_trab - total_contratual) + "  Itens: " + String.valueOf(total_itens) + " Comissão: " + CoreSFAndroid.formataMoeda(total_comissao));
        }

        if (GUIProdutosPedido.btvalor == null) {
            GUIProdutosPedido.btvalor = new Button(ControllerSFAndroid.getInstancia().getContext());
            GUIProdutosPedido.btvalor.setText("Brt: " + CoreSFAndroid.formataMoeda(total_ped) + "  Des: " + CoreSFAndroid.formataMoeda(desc_trab) + "  DesC: " + CoreSFAndroid.formataMoeda(desc_contratual) + "  Liq: " + CoreSFAndroid.formataMoeda(total_ped - desc_trab - total_contratual) + "  Itens: " + String.valueOf(total_itens) + " Comissão: " + CoreSFAndroid.formataMoeda(total_comissao));
        } else {
            GUIProdutosPedido.btvalor.setText("Brt: " + CoreSFAndroid.formataMoeda(total_ped) + "  Des: " + CoreSFAndroid.formataMoeda(desc_trab) + "  DesC: " + CoreSFAndroid.formataMoeda(desc_contratual) + "  Liq: " + CoreSFAndroid.formataMoeda(total_ped - desc_trab - total_contratual) + "  Itens: " + String.valueOf(total_itens) + " Comissão: " + CoreSFAndroid.formataMoeda(total_comissao));
        }

        if (GUIPedidoCarrinho.btvalor == null) {
            GUIPedidoCarrinho.btvalor = new Button(ControllerSFAndroid.getInstancia().getContext());
            GUIPedidoCarrinho.btvalor.setText("Brt: " + CoreSFAndroid.formataMoeda(total_ped) + "  Des: " + CoreSFAndroid.formataMoeda(desc_trab) + "  DesC: " + CoreSFAndroid.formataMoeda(desc_contratual) + "  Liq: " + CoreSFAndroid.formataMoeda(total_ped - desc_trab - total_contratual) + "  Itens: " + String.valueOf(total_itens) + " Comissão: " + CoreSFAndroid.formataMoeda(total_comissao));
        } else {
            GUIPedidoCarrinho.btvalor.setText("Brt: " + CoreSFAndroid.formataMoeda(total_ped) + "  Des: " + CoreSFAndroid.formataMoeda(desc_trab) + "  DesC: " + CoreSFAndroid.formataMoeda(desc_contratual) + "  Liq: " + CoreSFAndroid.formataMoeda(total_ped - desc_trab - total_contratual) + "  Itens: " + String.valueOf(total_itens) + " Comissão: " + CoreSFAndroid.formataMoeda(total_comissao));
        }


    }

    public static String formataMoeda(double valor) {
        double divisor = Math.pow(10, 2);
        valor = valor * divisor;
        int inteiro = (int) valor;
        valor = inteiro / divisor;
        String vlr = String.valueOf(valor);
        return vlr;
    }

    public static int pegarUltimaSeq() {
        int pegarUltimaSeq = 1;
        if (lista_itensPedido.size() > 0) {
            for (int i = 0; i < lista_itensPedido.size(); i++) {

                DefaultListModel dd = new DefaultListModel();
                dd = lista_itensPedido.get(i);
                pegarUltimaSeq = dd.getSequencia_item() + 1;
            }
        } else {
            pegarUltimaSeq = 1;
        }

        return pegarUltimaSeq;

    }

    public static void criarPedido() {
        try {
            if (PEDIDO_CORRENTE == null) {
                PEDIDO_CORRENTE = new MBPM();
            }
        } catch (Exception var1) {
            ;
        }

    }

    public static void criaritensPedido() {
        try {
            if (ITENS_CORRENTE == null) {
                ITENS_CORRENTE = new MBPD();
            }
        } catch (Exception var1) {
            ;
        }

    }

    public static CoreSFAndroid getintance() {
        return intance;
    }

    public static void criarFonte() {
        try {
            List<FonteEquipamento> lfont2 = LocatorDAO.getInstancia()
                    .getFonteEquipamento().findAll();
            Iterator<FonteEquipamento> var22 = lfont2.iterator();
            while (var22.hasNext()) {
                FonteEquipamento var3 = var22.next();
                FONTE_CORRENTE = LocatorDAO.getInstancia()
                        .getFonteEquipamento().findById(var3.getId());
                tamanho_fonte = var3.gettamanho();
            }
            if (FONTE_CORRENTE == null) {
                FONTE_CORRENTE = new FonteEquipamento();
                FONTE_CORRENTE.settamanho(14);
                tamanho_fonte = FONTE_CORRENTE.gettamanho();
                FONTE_CORRENTE = LocatorDAO.getInstancia()
                        .getFonteEquipamento().salvar(FONTE_CORRENTE);
            }
        } catch (Exception var1) {
            ;
        }

    }

    public static void enviar_Assinatura(final String diretorio, final String nomeArquivo) {
        new Thread() {
            public void run() {
                try {
                    uploadFTP("189.57.124.26", "mobile", "agrindus", "/assinatura", diretorio, nomeArquivo);
                    handler.post(new Runnable() {
                        public void run() {
                            ControllerSFAndroid.getInstancia().showMensagemSimples("ASSINATURA ENVIADA COM SUCESSO...");
                        }
                    });
                } catch (Exception e) {
                    Log.e("tag", e.getMessage());
                }
            }
        }.start();

    }

    @SuppressWarnings("resource")
    public static boolean uploadFTP(String ipFTP, String loginFTP, String senhaFTP, String diretorioFTP, String diretorioAndroid, String arquivoFTP) {


        try {
            File file = new File(diretorioAndroid);
            File file2 = new File(diretorioAndroid + arquivoFTP);

            if (file.isDirectory()) {
                if (file.list().length > 0) {
                    FTPClient ftp = new FTPClient();

                    ftp.connect(ipFTP, 21);
                    ftp.login(loginFTP, senhaFTP);
                    ftp.setBufferSize(49152);
                    ftp.enterLocalActiveMode();
                    ftp.setFileTransferMode(FTP.IMAGE_FILE_TYPE);
                    ftp.setFileType(FTPClient.IMAGE_FILE_TYPE);
                    //Verifica se o caminho/pasta n?o existe.
                    //seta o caminho no qual o servidor ir? trabalhar
                    boolean isDirectory = ftp.changeWorkingDirectory(diretorioFTP);
                    if (!isDirectory) {
                        ftp.makeDirectory(diretorioFTP);
                    }
                    ftp.changeWorkingDirectory(diretorioFTP);

                    FileInputStream arqEnviar = new FileInputStream(diretorioAndroid + arquivoFTP);

                    if (ftp.storeFile(arquivoFTP, arqEnviar)) {
                        ftp.logout();
                        ftp.disconnect();
                        if (file2.delete()) {
                            Log.i(TAG, "Arquivo " + arquivoFTP + " exclu?do com sucesso");
                            return true;
                        } else {
                            Log.i(TAG, "Erro ao excluir o arquivo " + arquivoFTP);
                            return false;
                        }
                    } else {
                        ftp.logout();
                        ftp.disconnect();
                        Log.i(TAG, "ERRO: arquivo " + arquivoFTP + "n?o foi enviado!");
                        return false;
                    }
                } else {
                    Log.i(TAG, "N?o existe o arquivo " + arquivoFTP + "neste diret?rio!");
                    return false;
                }
            } else {
                Log.i(TAG, "N?o ? diret?rio");
                return false;
            }
        } catch (Exception e) {
            Log.i(TAG, "ERRO FTP: " + e);
            return false;
        }


    }

    //retorna o dia da semana dada uma data
    public String retornarDiaSemana(int ano, int mes, int dia) {

        Calendar calendario = new GregorianCalendar(ano, mes - 1, dia);
        int diaSemana = calendario.get(Calendar.DAY_OF_WEEK);

        return pesquisarDiaSemana(diaSemana);
    }

    //faz a pesquisa, dado um inteiro de 1 a 7
    public String pesquisarDiaSemana(int _diaSemana) {
        String diaSemana = null;

        switch (_diaSemana) {

            case 1: {
                diaSemana = "Domingo";
                break;
            }
            case 2: {
                diaSemana = "Segunda";
                break;
            }
            case 3: {
                diaSemana = "Terça";
                break;
            }
            case 4: {
                diaSemana = "Quarta";
                break;
            }
            case 5: {
                diaSemana = "Quinta";
                break;
            }
            case 6: {
                diaSemana = "Sexta";
                break;
            }
            case 7: {
                diaSemana = "Sábado";
                break;
            }

        }
        return diaSemana;

    }

    public void abrirTelaLogin() {
        Intent i = new Intent(ControllerSFAndroid.getInstancia().getContext(), GUILogin.class);
        ControllerSFAndroid.getInstancia().getContext().startActivity(i);
    }

    public void abrirTelaFastSync() {
        Intent i = new Intent(ControllerSFAndroid.getInstancia().getContext(), GUIFastSync.class);
        ControllerSFAndroid.getInstancia().getContext().startActivity(i);
    }

    public void abrirTelaMsglog() {
        Intent var1 = new Intent(ControllerSFAndroid.getInstancia().getContext(), GUIMsgLog.class);
        ControllerSFAndroid.getInstancia().getContext().startActivity(var1);
    }

    public void abrirTelaPrincipalFastSync() {
        Intent i = new Intent(ControllerSFAndroid.getInstancia().getContext(), GUIPrincipalFastSync.class);
        ControllerSFAndroid.getInstancia().getContext().startActivity(i);
    }

    public void abrirTelaPrincipal() {
        Intent i = new Intent(ControllerSFAndroid.getInstancia().getContext(), GUIPrincipal.class);
        ControllerSFAndroid.getInstancia().getContext().startActivity(i);
    }

    public void abrirTelaGrupoPrincipal() {
        Intent var1 = new Intent(ControllerSFAndroid.getInstancia().getContext(), GUIGrupoProdutoPrincipal.class);
        ControllerSFAndroid.getInstancia().getContext().startActivity(var1);
    }

    public void abrirTelaPrincipalCliente() {
        Intent i = new Intent(ControllerSFAndroid.getInstancia().getContext(), GUITabInfoCliente.class);
        ControllerSFAndroid.getInstancia().getContext().startActivity(i);
    }

    public void abrirTelaListaCliente() {
        Intent i = new Intent(ControllerSFAndroid.getInstancia().getContext(), GUIListaCliente.class);
        ControllerSFAndroid.getInstancia().getContext().startActivity(i);
    }

    public void abrirTelaDetalheCliente() {
        Intent i = new Intent(ControllerSFAndroid.getInstancia().getContext(), GUIDetalheCliente.class);
        ControllerSFAndroid.getInstancia().getContext().startActivity(i);
    }

    public void abrirTelaPrincipalPedido() {
        Intent i = new Intent(ControllerSFAndroid.getInstancia().getContext(), GUITabPedido.class);
        ControllerSFAndroid.getInstancia().getContext().startActivity(i);
    }

    public void abrirTelaCadastroCliente() {
        Intent i = new Intent(ControllerSFAndroid.getInstancia().getContext(), GUICadastroCliente.class);
        ControllerSFAndroid.getInstancia().getContext().startActivity(i);
    }

    public void abrirTelaListaCadastroCliente() {
        Intent i = new Intent(ControllerSFAndroid.getInstancia().getContext(), GUIListaClienteCadastrado.class);
        ControllerSFAndroid.getInstancia().getContext().startActivity(i);
    }

    public void abrirTelalistaped() {
        Intent i = new Intent(ControllerSFAndroid.getInstancia().getContext(), GUIListaStatusPedido.class);
        ControllerSFAndroid.getInstancia().getContext().startActivity(i);
    }

    public void abrirTelaGrupoProduto() {
        Intent i = new Intent(ControllerSFAndroid.getInstancia().getContext(), GUIGrupoProdutos.class);
        ControllerSFAndroid.getInstancia().getContext().startActivity(i);
    }

    public void abrirTelaRotaCliente() {
        Intent i = new Intent(ControllerSFAndroid.getInstancia().getContext(), GUIRotasCliente.class);
        ControllerSFAndroid.getInstancia().getContext().startActivity(i);
    }

    public void abrirTelaProdutoPrincipal() {
        Intent var1 = new Intent(ControllerSFAndroid.getInstancia().getContext(), GUIListaProdutoPrincipal.class);
        ControllerSFAndroid.getInstancia().getContext().startActivity(var1);
    }

    public void abrirTelaGUIAssinatura() {
        Intent i = new Intent(ControllerSFAndroid.getInstancia().getContext(), GUIAssinatura.class);
        ControllerSFAndroid.getInstancia().getContext().startActivity(i);
    }

    public void abrirTelaCamera() {
        Intent i = new Intent(ControllerSFAndroid.getInstancia().getContext(), FotoCamera.class);
        Bundle s = new Bundle();
        ControllerSFAndroid.getInstancia().getContext().startActivity(i);
    }

    public void abrirTelaGaleria() {
        Intent i = new Intent(ControllerSFAndroid.getInstancia().getContext(), GaleriaImagem.class);
        ControllerSFAndroid.getInstancia().getContext().startActivity(i);
    }

    public void abrirTelaGrid() {
        Intent i = new Intent(ControllerSFAndroid.getInstancia().getContext(), GridImagens.class);
        ControllerSFAndroid.getInstancia().getContext().startActivity(i);
    }

    public void abrirTelaFullImagem() {
        Intent i = new Intent(ControllerSFAndroid.getInstancia().getContext(), TouchImagem.class);
        ControllerSFAndroid.getInstancia().getContext().startActivity(i);
    }

    public boolean isCarrinho(int id) {
        return this.mCarrinho.containsKey(String.valueOf(id));
    }

    public void inserirPedido() {
        DataEntrega = "";
        CoreSFAndroid.preco_lista = "";
        if (CoreSFAndroid.dcl.getNome() == null) {
            Toast.makeText(null, "Selecione Cliente Valido!",
                    Toast.LENGTH_SHORT).show();
        } else {

            CoreSFAndroid.PEDIDO_CORRENTE = null;
            CoreSFAndroid.getintance();
            CoreSFAndroid.criarPedido();
            CoreSFAndroid.PEDIDO_CORRENTE.setTipo_ped("N");

            // Pedido Novo = 1 / Pedido Update = 2
            CoreSFAndroid.controler_pedido = 1;
            if (CoreSFAndroid.lista_itensPedido != null) {
                CoreSFAndroid.lista_itensPedido.clear();
            }
            if (CoreSFAndroid.mCarrinho != null) {
                CoreSFAndroid.mCarrinho.clear();
            }

            CoreSFAndroid.flag_grupo = 2;
            CoreSFAndroid.lista_prod.clear();
            CoreSFAndroid.mProdutos.clear();
            MBPRDAO.getInstancia().offset = 0;
            MBPRDAO.getInstancia().nome = "";
            CoreSFAndroid.getintance().addProdList();

            try {
                MBCP cond = LocatorDAO.getInstancia().getMbcp().findCodPagto(CoreSFAndroid.Cond_Pagto);
                if (!(cond == null)) {
                    CoreSFAndroid.PEDIDO_CORRENTE.setCond_pagto(CoreSFAndroid.Cond_Pagto);
                    CoreSFAndroid.CONDPAGTO = cond.getCond_pgto() + " - " + cond.getDescricao();
                    CoreSFAndroid.desc_condicao = cond.getUsa_desc();

                }
            } catch (SFAndroidException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                MBTM tipo = LocatorDAO.getInstancia().getMbtm().findTipoCliente(CoreSFAndroid.tipo_cliente.trim());
                if (!(tipo == null)) {
                    CoreSFAndroid.PEDIDO_CORRENTE.setTipo_cli(CoreSFAndroid.tipo_cliente.trim());
                    CoreSFAndroid.PEDIDO_CORRENTE.setReservado2(Integer.parseInt(tipo.getTIPO_MOVTO()));
                    CoreSFAndroid.TCLIENTE = tipo.getTIPO_MOVTO() + " - " + tipo.getDESCRICAO();
                }
            } catch (SFAndroidException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                ltp = LocatorDAO.getInstancia().getMbTp().findAll();
                for (Iterator<MBTP> iterator = ltp.iterator(); iterator.hasNext(); ) {
                    MBTP tpgto = iterator.next();
                    if ("V".equals(tpgto.getEmissao())) {
                        CoreSFAndroid.TPGTO = tpgto.getEmissao() + " - " + tpgto.getDescricao();
                    }
                }
            } catch (SFAndroidException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

            CoreSFAndroid.getintance().abrirTelaPrincipalPedido();

        }
    }

    public void loadRoteiro() {


    }

    public void loadCliente() {
        try {
            lCli = LocatorDAO.getInstancia().getMbcl().findSelect();
            for (Iterator<MBCL> iterator = lCli.iterator(); iterator.hasNext(); ) {
                MBCL var3 = iterator.next();
                final String idO = String.valueOf(var3.getId());
                if (!CoreSFAndroid.mCliente.containsKey(idO)) {
                    CoreSFAndroid.mCliente.put(idO, var3);

                    dcl = new DefaultListModel();
                    dcl.setNome(var3.getRaz_social());
                    dcl.setMetaDataTag(var3.getTipo_cli());
                    dcl.setDescricao(var3.getCliente_alfa());
                    dcl.setNm_lista(var3.getPrioridade());
                    dcl.setScor(var3.getCond_pagto());
                    dcl.setSequencia_item(var3.getDesconto());
                    dcl.setFrete(var3.getRisco());
                    dcl.setObjeto(var3);

                    lista_cliente.add(dcl);

                } else {
                    CoreSFAndroid.mCliente.remove(idO);
                }
            }

        } catch (SFAndroidException var4) {
            var4.printStackTrace();
        }

    }

    public void loadClientePesq() {
        try {
            lCli = LocatorDAO.getInstancia().getMbcl().findSelectPesq();
            for (Iterator<MBCL> iterator = lCli.iterator(); iterator.hasNext(); ) {
                MBCL var3 = iterator.next();
                final String idO = String.valueOf(var3.getId());
                if (!CoreSFAndroid.mCliente.containsKey(idO)) {
                    CoreSFAndroid.mCliente.put(idO, var3);

                    dcl = new DefaultListModel();
                    dcl.setNome(var3.getRaz_social());
                    dcl.setMetaDataTag(var3.getTipo_cli());
                    dcl.setDescricao(var3.getCliente_alfa());
                    dcl.setNm_lista(var3.getPrioridade());
                    dcl.setScor(var3.getCond_pagto());
                    dcl.setSequencia_item(var3.getDesconto());
                    dcl.setFrete(var3.getRisco());
                    dcl.setObjeto(var3);
                    lista_cliente.add(dcl);

                } else {
                    CoreSFAndroid.mCliente.remove(idO);
                }
            }
        } catch (SFAndroidException var4) {
            var4.printStackTrace();
        }

    }

    public void addProdList() {
        try {
            lP = LocatorDAO.getInstancia().getMbpr().findSelect();
            for (Iterator<MBPR> iterator2 = lP.iterator(); iterator2.hasNext(); ) {
                MBPR pt = iterator2.next();
                final String idO = String.valueOf(pt.getId());

                if (!CoreSFAndroid.mProdutos.containsKey(idO)) {
                    CoreSFAndroid.mProdutos.put(idO, pt);
                    dd = new DefaultListModel();
                    dd.setMetaDataTag(pt.getC_prod_palm());
                    dd.setNome(String.valueOf(pt.getId()));
                    dd.setDescricao(pt.getDescricao());
                    double preco = 0.00;
                    double precoOrig = 0.00;
                    MBLP pr = null;
                    try {
                        pr = LocatorDAO.getInstancia().getMblp().findSelect(CoreSFAndroid.nm_lista, pt.getC_prod_palm());
                        if (!(pr == null)) {
                            preco = pr.getPRECO();
                        } else {
                            preco = 0.00;
                        }
                        pr = LocatorDAO.getInstancia().getMblp().findSelect(CoreSFAndroid.nm_listaVend, pt.getC_prod_palm());
                        if (!(pr == null)) {
                            precoOrig = pr.getPRECO();
                        } else {
                            precoOrig = 0.00;
                        }
                    } catch (SFAndroidException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    MBDE de = LocatorDAO.getInstancia().getMbde().findSelect(CoreSFAndroid.cod_cliente, pt.getC_prod_palm());
                    if (!(de == null)) {
                        double desc = de.getPORC_DESC() / 100;
                        dd.setdescTrab(desc * preco);
                        double preco_desc = preco - (desc * preco);
                        dd.setValor(CoreSFAndroid.formataMoeda(preco_desc));
                        dd.setvalorCliente(CoreSFAndroid.formataMoeda(preco_desc));
                        dd.setValorOriginal(CoreSFAndroid.formataMoeda(precoOrig));
                    } else {
                        dd.setdescTrab(0.00);
                        dd.setvalorCliente(CoreSFAndroid.formataMoeda(preco));
                        dd.setValorOriginal(CoreSFAndroid.formataMoeda(precoOrig));
                        dd.setValor(CoreSFAndroid.formataMoeda(preco));
                    }

                    //dd.setQuantidade(1);
                    dd.setFrete(pt.getPosicao_estq());
                    dd.setObjeto(pt);

                    lista_prod.add(dd);
                } else {
                    CoreSFAndroid.mProdutos.remove(idO);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



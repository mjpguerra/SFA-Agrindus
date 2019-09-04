package com.mobilefast.sfandroid.model;

import com.mobilefast.sfandroid.exception.SFAndroidException;

public class LocatorDAO {

	private static LocatorDAO instancia = null;
	private MBPDDAO MbPdDao = null;
	private MBPMDAO MbPmDao = null;
	private MBTPDAO MbTpDao = null;
	private MBCLDAO MbclDao = null;
	private MBCPDAO MbcpDao = null;
	private MBGRDAO MbgrDao = null;
	private MBPRDAO MbprDao = null;
	private MBVNDAO MbvnDao = null;
	private MsgLogDAO MsglogDAO = null;
	private FastSyncDAO fastSyncDAO = null;
	private FonteEquipamentoDAO fonteEquipamentoDAO = null;
	private MBLPDAO MblpDao = null;
	private MBDEDAO MbdeDao = null;
	private MBCDCLDAO MbcdclDao = null;
	private MBTMDAO MbtmDao = null;

	private LocatorDAO() {
		super();
	}

	public static LocatorDAO getInstancia() {
		if (instancia == null) {
			instancia = new LocatorDAO();
		}

		return instancia;
	}

	public MBTMDAO getMbtm() throws SFAndroidException {
		if (MbtmDao == null) {
			MbtmDao = MBTMDAO.getInstancia();
		}

		return MbtmDao;
	}

	public MBCDCLDAO getMbcdcl() throws SFAndroidException {
		if (MbcdclDao == null) {
			MbcdclDao = MBCDCLDAO.getInstancia();
		}

		return MbcdclDao;
	}

	public MBDEDAO getMbde() throws SFAndroidException {
		if (MbdeDao == null) {
			MbdeDao = MBDEDAO.getInstancia();
		}

		return MbdeDao;
	}

	public MBLPDAO getMblp() throws SFAndroidException {
		if (MblpDao == null) {
			MblpDao = MBLPDAO.getInstancia();
		}

		return MblpDao;
	}

	public FonteEquipamentoDAO getFonteEquipamento() throws SFAndroidException {
		if (fonteEquipamentoDAO == null) {
			fonteEquipamentoDAO = FonteEquipamentoDAO.getInstancia();
		}

		return fonteEquipamentoDAO;
	}

	public FastSyncDAO getFastSync() throws SFAndroidException {
		if (this.fastSyncDAO == null) {
			this.fastSyncDAO = FastSyncDAO.getInstancia();
		}

		return this.fastSyncDAO;
	}

	public MBPDDAO getMbPd() throws SFAndroidException {
		if (this.MbPdDao == null) {
			this.MbPdDao = MBPDDAO.getInstancia();
		}

		return this.MbPdDao;
	}

	public MBPMDAO getMbPm() throws SFAndroidException {
		if (this.MbPmDao == null) {
			this.MbPmDao = MBPMDAO.getInstancia();
		}

		return this.MbPmDao;
	}

	public MBTPDAO getMbTp() throws SFAndroidException {
		if (this.MbTpDao == null) {
			this.MbTpDao = MBTPDAO.getInstancia();
		}

		return this.MbTpDao;
	}

	public MBCLDAO getMbcl() throws SFAndroidException {
		if (this.MbclDao == null) {
			this.MbclDao = MBCLDAO.getInstancia();
		}

		return this.MbclDao;
	}

	public MBCPDAO getMbcp() throws SFAndroidException {
		if (this.MbcpDao == null) {
			this.MbcpDao = MBCPDAO.getInstancia();
		}

		return this.MbcpDao;
	}

	public MBGRDAO getMbgr() throws SFAndroidException {
		if (this.MbgrDao == null) {
			this.MbgrDao = MBGRDAO.getInstancia();
		}

		return this.MbgrDao;
	}

	public MBPRDAO getMbpr() throws SFAndroidException {
		if (this.MbprDao == null) {
			this.MbprDao = MBPRDAO.getInstancia();
		}

		return this.MbprDao;
	}

	public MBVNDAO getMbvn() throws SFAndroidException {
		if (this.MbvnDao == null) {
			this.MbvnDao = MBVNDAO.getInstancia();
		}

		return this.MbvnDao;
	}

	public MsgLogDAO getMsgLog() throws SFAndroidException {
		if (this.MsglogDAO == null) {
			this.MsglogDAO = MsgLogDAO.getInstancia();
		}

		return this.MsglogDAO;
	}
}

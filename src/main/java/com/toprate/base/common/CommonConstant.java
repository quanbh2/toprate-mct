package com.toprate.base.common;

public class CommonConstant {
	public interface DATE_FORMAT {
		public final String FORMAT_DATETIME_DDMMYYYY ="dd/MM/yyyy";
		public final String FORMAT_DATETIME_DDMMYYYYsshh ="dd/MM/yyyy HH:mm";
	}
	
	public interface TOKEN_KEY {
		public final String VPS_USER_TOKEN_KEY = "vpsUserToken";
	}
	
	public interface PROC_PLAN_STATUS {
		public final Long DANG_LAP_KHLCNT = 1L;
		public final Long DANG_LAP_HSMT = 2L;
	}
	public interface CHECKLIST_DATA_REFERRENCE {
		public final Long IS_NOT_REFERRENCE = 0L;
		public final Long IS_REFERRENCE = 1L;
	}
	
	public interface PROC_PACKAGE_CHECKLIST_DATA {
//		1	Dự án
		public final Long DU_AN = 1L;
//		2	Điều chỉnh dự án
		public final Long DIEU_CHINH_DU_AN = 2L;
//		3	Chỉ tiêu kỹ thuật được duyệt (nếu có)
		public final Long CHI_TIEU_KY_THUAT_DUOC_DUYET = 3L;
//		4	Ngày giao thầu
		public final Long NGAY_GIAO_THAU = 4L;
//		5	Tờ trình xin phê duyệt kế hoạch lựa chọn nhà thầu (KH LCNT)
		public final Long TO_TRINH_XIN_PHE_DUYET_KE_HOACH_lUA_CHON_NHA_THAU = 5L;
//		6	Báo cáo thẩm định KH LCNT
		public final Long BAO_CAO_THAM_DINH_KHLCNT = 6L;
//		7	Quyết định phê duyệt KH LCNT
		public final Long QUYET_DINH_PHE_DUYET_KHLCNT = 7L;
//		8	Tờ trình xin điều chỉnh KH LCNT
		public final Long TO_TRINH_XIN_DIEU_CHINH_KHLCNT = 8L;
//		9	Báo cáo thẩm định điều chỉnh KH LCNT
		public final Long BAO_CAO_THAM_DINH_DIEU_CHINH_KHLCNT = 9L;
//		10	Quyết định phê duyệt điều chỉnh KH LCNT
//		11	Tờ trình thành lập tổ chuyên gia đấu thầu
//		12	Quyết định thành lập tổ chuyên gia đấu thầu
//		13	Tờ trình xin phê duyệt danh sách nhà thầu
//		14	Quyết định phê duyệt danh sách nhà thầu
//		15	Đăng danh sách nhà thầu
//		16	Tờ trình xin phê duyệt danh sách nhà thầu
		public final Long TO_TRINH_XIN_PHE_DUYET_DSNT = 16L;
//		17	Tờ trình xin phê duyệt hồ sơ mời thầu/HSYC
//		18	Báo cáo thẩm định hồ sơ mời thầu/HSYC
//		19	Quyết định phê duyệt hồ sơ mời thầu/HSYC
//		20	Đăng thông báo mời thầu (rộng rãi/CHCT)
//		21	Thư mời thầu, mời chào giá (Hạn chế/CĐT/MSTT/CHCTRG)
//		22	Thư mời thương thảo hợp đồng gói chỉ định thầu rút gọn
//		23	Phát hành hồ sơ mời thầu/ hồ sơ yêu cầu
		public final Long PHAT_HANH_HO_SO_MOI_THAU = 26L;
//		24	Tờ trình xin điều chỉnh hồ sơ mời thầu
//		25	Báo cáo thẩm định điều chỉnh HSMT
//		26	Quyết định điều chỉnh hồ sơ mời thầu
//		27	Thông báo gia hạn phát hành HSMT/HSYC (có thể trước hoặc sau xử lý tình huống cho phép mở thầu)
//		28	Tờ trình xử lý tình huống (cho phép mở thầu hoặc gia hạn thầu - trong trường hợp thời điểm đóng thầu có ít hơn 03 nhà thầu tham dự thầu). 
//		29	Tờ trình xử lý tình huống lần 2 (gia hạn nhưng vẫn chỉ có ít hơn 03 nhà thầu tham dự thầu)
//		30	Ngày mở thầu/Mở HSĐXKT
		public final Long NGAY_MO_THAU = 33L;
		
//		31	Biên bản mở thầu/Mở HSĐXKT/Văn bản tiếp nhận báo giá (CHCT RG)
		public final Long BIEN_BAN_MO_THAU = 34L;
//		32	Báo cáo đánh giá HSĐXKT (2 túi HS)
//		33	Báo cáo thẩm định kết quả đánh giá hồ sơ đề xuất kỹ thuật (2 túi HS)
//		34	Quyết định phê duyệt danh sách các nhà thầu đạt về mặt kỹ thuật (2 túi HS)
//		35	Tờ trình xử lý tình huống
//		36	Thông báo danh sách đạt kỹ thuật
//		37	Biên bản mở thầu (HSDX tài chính) (2 túi HS)
//		38	Tờ trình xin phê duyệt danh sách xếp hạng nhà thầu
//		39	Quyết định phê duyệt danh sách xếp hạng nhà thầu
//		40	Thư mời thương thảo hợp đồng đối với gói thầu RR/HC/CHCT
//		41	Biên bản thương thảo hợp đồng
//		42	Tờ trình xử lý tình huống
//		43	Báo cáo đánh giá hồ sơ dự thầu (RR, HC), hồ sơ đề xuất (CHCT, MSTT, CDT); b/c KQ thương thảo (CĐT RG)
//		44	Báo cáo thẩm định kết quả lựa chọn nhà thầu gói thầu (trừ CDT RG)
//		45	Quyết định phê duyệt kết quả lựa chọn nhà thầu
//		46	Quyết định hủy thầu
//		47	Đăng thông báo kết quả lựa chọn nhà thầu
//		48	Thông báo kết quả lựa chọn nhà thầu
//		49	Thông báo hủy thầu
//		50	Biên bản hoàn thiện hợp đồng (nếu có)
//		51	Báo cáo kết quả đàm phán hợp đồng (trừ CĐT RG)
		public final Long BAO_CAO_KET_QUA_DAM_PHAN_HOP_DONG = 51L;
//		52	Hợp đồng
		public final Long HOP_DONG = 52L;
		public final Long BAO_CAO_THAM_DINH_KET_QUA_LUA_CHON_NHA_THAU_GOI_THAU = 47L;
//		45	Quyết định phê duyệt kết quả lựa chọn nhà thầu
		public final Long QUYET_DINH_PHE_DUYET_KET_QUA_LUA_CHON_NHA_THAU = 48L;
		
	}
	public interface IMS_CONTRACT_HANDOVER_STATUS {
		public final String REQUEST_APPROVE ="2";
	}
	
	public interface TAPDOAN {
		public final String CODE ="TAPDOAN";
	}
	
	public interface IMS_INVEST_PLAN_STATUS {
		public final String CREATE ="0";
		public final String REQUEST_APPROVE ="1";
		public final String APPROVE ="2";
		public final String REJECT ="3";
	}
	
	public interface IMS_INVEST_PLAN_STATUS_TYPE {
		public final Long CREATE = 0L;
		public final Long REQUEST_APPROVE = 1L;
		public final Long APPROVE = 2L;
		public final Long REJECT = 3L;
	}
	public interface STATUS_CA {
		public final Long CREATE = 0L;
		public final Long REQUEST_APPROVE = 1L;
		public final Long APPROVE = 2L;
		public final Long REJECT = 3L;
	}
	public interface INVEST_ITEM_REPORT_TYPE { 
		public final String TYPE_1 ="1";
		public final String TYPE_2 ="2";
		public final String TYPE_3 ="3";
	}

	public interface STATUS { 
		public final String CREATE ="0";
		public final String REQUEST_APPROVE ="1";
		public final String APPROVE ="3";
		public final String REJECT ="2";
	}
	public interface PROC_BIDDER_TYPE {
		public final String NT_DKT ="1";
		public final String NT_MUAHS ="2";
	}
	
	
	
	public interface STATUS_CODE {
		public final String CREATE ="0";
		public final String REQUEST_APPROVE ="1";
		public final String APPROVE ="2";
		public final String REJECT ="3";
		public final String CANCEL ="4";
		public final String LAW_PROFILE ="9";
	}
	
	public interface DOCUMENT_TYPE {
		public final Long CHU_TRUONG_DAU_TU = 1L;
		public final Long CNT_PROPOSAL = 2L;
		public final Long PROC_PLAN_SUBMIT_REPORT = 3L;
		public final Long PROC_PLAN_REVIEW_REPORT = 4L;
		public final Long PROC_PLAN_APPROVE = 5L;
		public final Long DU_AN_KHCN = 6L;
		public final Long DU_AN_DAU_TU = 7L;
		public final Long DU_AN_KHCN_POLICY = 8L;
		public final Long DU_AN_KHCN_APPROVAL = 9L;
		public final Long DU_AN_KHCN_APPROVAL_ADJUSTMENT = 10L;
		public final Long QD_LC_XL = 11L;
		public final Long QD_LC_MS = 12L;
		public final Long BBTT_XL = 13L;
		public final Long BBTT_MS = 14L;
		public final Long PRO_CONSTRUCT  = 15L;
		public final Long PRO_PURCHASE  = 16L;
		public final Long CONSTR_ESTI_BUDGET  = 17L;
		public final Long CHU_TRUONG_DAU_TU_APPROVAL = 18L;
		public final Long GOI_THAU_MUA_SAM_DIEU_CHINH_HO_SO_MOI_THAU = 19L;
		//duongvt
		public final Long HD_XL = 20L;
		public final Long GOI_THAU_MUA_SAM_CAC_LOAI_HO_SO = 21L;
		public final Long RENT_POSITION_CONTRACT = 22L;
		public final Long PARTNER_PURCHASE  = 24L;
		public final Long HD_MS_TX = 25L;
		public final Long GTMS_TO_TRINH_THANH_LAP_TCG = 26L;
		public final Long GTMS_QD_THANH_LAP_TCG = 27L;
		public final Long GTMS_TO_TRINH_XIN_PHE_DUYET_DS_NHA_THAU = 28L;
		public final Long GTMS_QUYET_DINH_PHE_DUYET_DS_NHA_THAU = 29L;
		public final Long GTMS_CHI_TIEU_KI_THUAT_HSMT = 30L;
		public final Long GTMS_TO_TRINH_XIN_PHE_DUYET_HSMT = 31L;
		public final Long GTMS_BC_THAM_DINH_HSMT = 32L;
		public final Long GTMS_QD_PHE_DUYET_HSMT = 33L;
		public final Long GTMS_CAC_VAN_BAN_LAM_RO_HSMT = 34L;
		public final Long HD_DauTu_BL = 34L;
		public final Long HD_DauTu_BH = 35L;
		public final Long HOP_DONG_MUA_SAM = 36L;
		public final Long HD_ThongTin_ThanhLy= 37L;
		public final Long LUA_CHON_NHA_THAU_MS = 38L;
		public final Long TO_TRINH = 39L;
		public final Long BAO_CAO_THAM_DINH = 40L;
		public final Long QUYET_DINH_PHE_DUYET = 41L;
		public final Long RENT_POSITION_CONTRACT_FINISH = 42L;
		public final Long TAI_LIEU_DANH_GIA_KY_THUAT = 43L;
		public final Long TO_TRINH_PHE_DUYET_DS_NT_DAPUNG_KT = 44L;
		public final Long BAO_CAO_THAM_DINH_KQ_DANH_GIA = 45L;
		public final Long QUYET_DINH_PHE_DUYET_DS_NT = 46L;
		public final Long BIEN_BAN_MO_HS_TAI_CHINH = 47L;
		public final Long TO_TRINH_PHE_DUYET_DANH_SACH_XEP_HANG_NT = 48L;
		public final Long QUYET_DINH_PHE_DUYET_DANH_SACH_XEP_HANG_NT = 49L;
		public final Long TAI_LIEU_PHE_DUYET_XEP_HANG = 50L;
		public final Long BIEN_BAN_THUONG_THAO_HD = 51L;
		public final Long BIEN_BAN_DAM_PHAN = 52L;
		
		
		public final Long BAO_CAO_KQ_DANH_GIA = 53L;
		public final Long BAO_CAO_THAM_DINH_KQ_DANH_GIA_HSDT = 54L;
		public final Long QUYET_DINH_PHE_DUYET_KQ_LCNT = 55L;
		public final Long TB_KQ_DAU_THAU = 56L;
		public final Long CAC_VAN_BAN_XU_LY_TINH_HUONG = 57L;
		public final Long KHAC = 58L;
		public final Long THONG_TIN_CHUNG_KQ_LCNT = 59L;
		
		public final Long CHU_TRUONG_DAU_TU_VERIFY = 60L;
		public final Long THAM_DINH_KE_HOACH_MS = 61L;
		
		
		//merge
		public final Long KE_HOACH_LUA_CHON_NHA_THAU_MS_TO_TRINH = 62L;
		public final Long KE_HOACH_LUA_CHON_NHA_THAU_MS_BAO_CAO_THAM_DINH = 63L;
		public final Long KE_HOACH_LUA_CHON_NHA_THAU_MS_QUYET_DINH_PHE_DUYET = 64L;
		
		
		//QUANGTAO-Fix bug attach file
		public final Long FILE_HO_SO_YEU_CAU = 65L;
		public final Long FILE_LAM_RO_HO_SO_YEU_CAU = 66L;
		public final Long FILE_THONG_BAO_GIA_HAN = 67L;
		public final Long FILE_PHE_DUYET_HUY_THAU = 68L;
		public final Long FILE_THONG_BAO_HUY_THAU = 69L;
		public final Long FILE_DINH_KEM_THONG_BAO_KET_QUA = 70L;
		public final Long THONG_TIN_GOI_THAU_MUA_SAM_ATTACH_FILE = 71L;
		
		
		public final Long GOI_THAU_DANG_TAI = 72L;
		public final Long THAM_DINH_GOI_THAU_DANG_TAI = 73L;
		public final Long HO_SO_YEU_CAU = 74L;
		public final Long CAP_NHAT_THONG_TIN_GOI_THAU = 75L;
		public final Long KPI_DS_DU_AN = 76L;
		
		public final Long DU_AN_DAU_TU_PHE_DUYET = 77L;
		public final Long BO_SUNG_THONG_TIN_DU_AN_CHI_TIEU_KY_THUAT = 78L;
		public final Long QD_PHE_DUYET_CHU_TRUONG_DAU_TU = 79L;
		public final Long QD_PHE_DUYET_DU_AN_DAU_TU = 80L;

		public final Long KE_HOACH_THUC_HIEN_DU_AN = 84L;
		
		public final Long TO_TRINH_DAU_TU_MUA_SAM = 81L;
		public final Long TO_TRINH_DAU_TU_MUA_SAM_YEU_CAU_PHE_DUYET = 82L;
		public final Long TO_TRINH_DAU_TU_MUA_SAM_PHE_DUYET = 83L;
		public final Long TRINH_KY_TO_TRINH_DAU_TU_MUA_SAM = 85L;
		public final Long TRINH_KY_CHINH_TO_TRINH_DAU_TU_MUA_SAM = 86L;
	}
	
	public interface STATUS_PACKAGE {
		public final String APPROVAL_USER ="2";
		public final String REJECT_USER ="3";
		public final String REQUEST_APPROVE ="2";
	}
	public interface IS_PAID_REG_FILE {
		public final String IS_PAID_REG_FILE ="1";
	}
	public interface UPPLOAD_IS_HAVED_REQ_FILE {
		public final String IS_HAVED_REQ_FILE ="1";
		public final String NOT_HAVED_REQ_FILE ="0";
	}
	
	//TrungBH - start
	public interface CAT_PROCUREMENT_FORMS{
		public final String NAME = "PROCUREMENT_FORMS_IMS_TYPE";
		public final Long CHCT = 3L;
		public final Long DTRR = 1L;
	}
	
	public interface PROC_PACKAGE_REPORT_TYPE{
		public final Long GOI_THAU_CHAM_TIEN_DO = 1L;
		public final Long GOI_THAU_CHAM_PHAT_HANH = 2L;
		public final Long GOI_THAU_GAN_DEN_NGAY_PHAT_HANH = 3L;
		public final Long GOI_THAU_DANG_CHAM_THAU = 4L;
		public final Long GOI_THAU_DANG_PHAT_HANH = 5L;
		public final Long GOI_THAU_DA_HOAN_THANH = 6L;
		public final Long GOI_THAU_DA_PHE_DUYET = 7L;
	}
	
	public interface CAT_FILE_INVOICE_ID{
		public final Long PHAT_HANH_GOI_THAU = 26L;
		public final Long DA_MO_THAU = 33L;
		public final Long PHE_DUYET_KQLCNT = 48L;
	}
	
	public interface BIEU_MAU {
		public final String BIEU_MAU_HANG_HOA_DU_TOAN = "PROC_PACKAGE_ESTIMATE_ITEMS_TEMPLATE.xls";
		public final String BIEU_MAU_HANG_MUC_DU_TOAN_GOI_THAU = "TEMP_IMP_HMDTGT.xls";
		
	}
	
	public interface ADJ_HISTORY_TYPE {
		public static final String ADJ_TYPE_ADD = "1";
	    public static final String ADJ_TYPE_EDIT = "2";
	    public static final String ADJ_TYPE_DELETE = "0";
	}
	
	public interface PROC_PACKAGE_STATUS{
		public final String NAME = "PROC_PACKAGE_STATUS";
		public final Long DANG_LAP_KHLCNT = 1L;
		public final Long DANG_LAP_HSMT = 2L;
		public final Long DANG_PHAT_THAU = 3L;
		public final Long DANG_CHAM_THAU = 4L;
		public final Long DA_BAO_CAO_KET_QUA = 5L;
		public final Long DA_PHE_DUYET_KET_QUA = 6L;
		public final Long HOAN_THANH = 7L;
		public final Long HUY_THAU = 8L;
		public final Long CHO_PHE_DUYET_HOAN_THANH = 9L;
		public final Long TU_CHOI_PHE_DUYET_HOAN_THANH = 10L;
	}
	public static final Long[] PROC_PACKAGE_REPORT_TYPES = {PROC_PACKAGE_REPORT_TYPE.GOI_THAU_CHAM_TIEN_DO,
			PROC_PACKAGE_REPORT_TYPE.GOI_THAU_CHAM_PHAT_HANH,
			PROC_PACKAGE_REPORT_TYPE.GOI_THAU_GAN_DEN_NGAY_PHAT_HANH,
			PROC_PACKAGE_REPORT_TYPE.GOI_THAU_DANG_CHAM_THAU,
			PROC_PACKAGE_REPORT_TYPE.GOI_THAU_DANG_PHAT_HANH,
			PROC_PACKAGE_REPORT_TYPE.GOI_THAU_DA_HOAN_THANH,
			PROC_PACKAGE_REPORT_TYPE.GOI_THAU_DA_PHE_DUYET}; 
	//TrungBH - end
	
	
	public interface ACTION_INVEST_PROJECT{
		public final String CREATE_NEW="CREATE_NEW";
		public final String EDIT="EDIT";
		public final String APPROVAL="APPROVAL";
		public final String ADD_DOCS="ADD_DOCS";
		public final String EDIT_AFTER_APP="EDIT_AFTER_APP";
	}
}
		


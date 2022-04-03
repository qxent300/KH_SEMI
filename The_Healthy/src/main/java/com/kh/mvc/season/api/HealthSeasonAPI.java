package com.kh.mvc.season.api;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.kh.mvc.season.model.vo.HealthSeason;

public class HealthSeasonAPI  {
	public static String URL = "http://211.237.50.150:7080/openapi/6393e577792419fab8d675e7d0516c87f48e6f7a9481a4c5ed9dd1c3b7f2a937/json/Grid_20171128000000000572_1/1/1000"; 

	public List<HealthSeason> callHealthSeasonListByJSON() {
		List<HealthSeason> list = new ArrayList<>();
		Map<String, String> map = makeImgMap();
		List<String> effectFilterList = makeEFFECTFilterList();
		
		try {
			URL url = new URL(URL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			int code = conn.getResponseCode();

			if (code < 200 || code > 300) {
				System.out.println("페이지가 잘못되었습니다.");
				return null;
			}

			InputStreamReader isr = new InputStreamReader(conn.getInputStream(), "UTF-8");
			BufferedReader br = new BufferedReader(isr);

			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObj = (JSONObject) jsonParser.parse(br);
			JSONObject result = (JSONObject) jsonObj.get("Grid_20171128000000000572_1");
			JSONArray rowArray = (JSONArray) result.get("row");

			for (int i = 0; i < rowArray.size(); i++) {
				JSONObject seasonObject = (JSONObject) rowArray.get(i);
				
				String PRDLST_NM = PRDLST_NMFilter(seasonObject.get("PRDLST_NM").toString()); // 품목명
				String M_DISTCTNS = seasonObject.get("M_DISTCTNS").toString(); // 월별
				String MTC_NM = seasonObject.get("MTC_NM").toString(); // 주요산지
				String EFFECT = EFFECTFilter(effectFilterList, PRDLST_NM, seasonObject.get("EFFECT").toString()); // 효능 
				String PURCHASE_MTH = PURCHASE_MTHFilter(seasonObject.get("PURCHASE_MTH").toString());// 구입요령
				String COOK_MTH = COOK_MTHFilter(seasonObject.get("COOK_MTH").toString()); // 조리법
				String TRT_MTH = TRT_MTHFilter(seasonObject.get("TRT_MTH").toString()); // 손질요령
				String IMG_URL = map.get(PRDLST_NM); // 이미지
				
				list.add(new HealthSeason(PRDLST_NM, M_DISTCTNS, MTC_NM, EFFECT, PURCHASE_MTH, COOK_MTH, TRT_MTH, IMG_URL));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	private String PRDLST_NMFilter(String inputPRDLST_NM) {
		String PRDLST_NM = inputPRDLST_NM;
		if(PRDLST_NM.equals("늙은 호박") != true) {
			PRDLST_NM = PRDLST_NM.replace(" ", "");
		}
		return PRDLST_NM;
	}
	
	private String TRT_MTHFilter(String inputTRT_MTH) {
		return inputTRT_MTH.replace("？", "").replace("\r\n", "");
	}
	
	private String COOK_MTHFilter(String inputCOOK_MTH) {
		return inputCOOK_MTH.replace("？", "").replace("\r\n", "");
	}
	
	private String PURCHASE_MTHFilter(String inputPURCHASE_MTH) {
		return inputPURCHASE_MTH.replace("？", "").replace("\r\n", "").replace("  ", " ");
	}

	private String EFFECTFilter(List<String> effectFilterList, String inputPRDLST_NM, String inputEFFECT) {
		String EFFECT = inputEFFECT.toString().replace("？", "");
		if(effectFilterList.contains(inputPRDLST_NM) == true) {
			if(inputPRDLST_NM.equals("방울양배추") == true) {
				EFFECT = "-" + inputEFFECT;
			} else {
				EFFECT = "- " + inputEFFECT;
			}
		}
		return EFFECT;
	}
	
	private List<String> makeEFFECTFilterList() {
		List<String> list = new ArrayList<>();
		list.add("산마늘");
		list.add("울외");
		list.add("밤콩");
		list.add("노각");
		list.add("삼채");
		list.add("방아잎");
		list.add("돼지감자");
		list.add("섬초");
		list.add("아마란스");
		list.add("솔부추");
		list.add("청경채");
		list.add("히카마");
		list.add("동아");
		list.add("청귤");
		list.add("호밀");
		list.add("구기자");
		list.add("보리수열매");
		list.add("체리");
		list.add("곰취");
		list.add("토마토");
		list.add("목이버섯");
		list.add("봄동");
		list.add("취청오이");
		list.add("레드향");
		list.add("방울양배추");
		return list;
	}

	private Map<String, String> makeImgMap() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("산마늘",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=412e6aee4c544a57ae8e32e145742c88fc5729350c3adae7b2e4695e33c785ff&fileSn=1");
		map.put("울외",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=412e6aee4c544a57ae8e32e145742c882423713dd94d85ed330e6d820d379fd6&fileSn=1");
		map.put("밤콩",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=412e6aee4c544a57ae8e32e145742c883d93995cd439ae2b0f67b94c150464b7&fileSn=1");
		map.put("노각",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=412e6aee4c544a57ae8e32e145742c88b241b4bec85d729bc56a2a581e39fe39&fileSn=1");
		map.put("삼채",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=412e6aee4c544a57ae8e32e145742c881de865a9b888283773891a64916b9823&fileSn=1");
		map.put("방아잎",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=412e6aee4c544a57ae8e32e145742c8832f42984a8d6c7d67a981cdbee371a55&fileSn=1");
		map.put("돼지감자",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=412e6aee4c544a57ae8e32e145742c8827f81d0955a986d543235db98698e6c3&fileSn=1");
		map.put("섬초",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=412e6aee4c544a57ae8e32e145742c88e70824e67124ca2c7bc6ce080168ea28&fileSn=1");
		map.put("아마란스",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=412e6aee4c544a57ae8e32e145742c8853f7a2292edfa42f69ce4d78335956e4&fileSn=1");
		map.put("솔부추",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=412e6aee4c544a57ae8e32e145742c88887cb5ad100b847147b9f5360a148af8&fileSn=1");
		map.put("청경채",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=412e6aee4c544a57ae8e32e145742c880af013e06d76d89354ca712a698d33a0&fileSn=1");
		map.put("히카마",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=412e6aee4c544a57ae8e32e145742c8875d7d44a6680947ed988420f4924c056&fileSn=1");
		map.put("동아",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=412e6aee4c544a57ae8e32e145742c886a16594945d0ca41c1082c7d4cacba62&fileSn=1");
		map.put("청귤",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=412e6aee4c544a57ae8e32e145742c8841aecf786d24ee889a4b7920f752f312&fileSn=1");
		map.put("호밀",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=412e6aee4c544a57ae8e32e145742c883dda909387ad19be9978fb999cbe2cd1&fileSn=1");
		map.put("구기자",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=412e6aee4c544a57ae8e32e145742c88897e460c4d77464d7fa3cf3c8a09dfd8&fileSn=1");
		map.put("보리수열매",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=412e6aee4c544a57ae8e32e145742c887ab4f15a7d3fe37e05f5cdb3eff07ff0&fileSn=1");
		map.put("체리",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=412e6aee4c544a57ae8e32e145742c88b7b58513ca0031009a8158e0af91835d&fileSn=1");
		map.put("곰취",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=412e6aee4c544a57ae8e32e145742c88575dd09de234ea5f88883f220b2e5b5a&fileSn=1");
		map.put("토마토",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=412e6aee4c544a57ae8e32e145742c88cf4c634a7a3a1194f2d7b65f32d1eca3&fileSn=1");
		map.put("목이버섯",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=412e6aee4c544a57ae8e32e145742c88903157afcc110ea6e198b9837a5cb626&fileSn=1");
		map.put("봄동",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=412e6aee4c544a57ae8e32e145742c88da6a0a10862df8c1bc40e38d828ef4d5&fileSn=1");
		map.put("취청오이",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=412e6aee4c544a57ae8e32e145742c88b098c2be31ec9312678f28ea9896f307&fileSn=1");
		map.put("레드향",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=412e6aee4c544a57ae8e32e145742c889e6f7f3999046cedac1dc5a365ff53f0&fileSn=1");
		map.put("방울양배추",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=412e6aee4c544a57ae8e32e145742c887f3fc86dfd74a131126f5c15ef7108ed&fileSn=1");
		map.put("기장",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551bc1ba1a24348dbd1a223e96dcb8a3c058&fileSn=1");
		map.put("밀",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551bec0a781bfb46511024affa6ee45dc186&fileSn=1");
		map.put("보리",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551b6f7ed80f11427048ec6acd7e42e792f5&fileSn=1");
		map.put("수수",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551b423014a3d4f268c6dc063227ef185dfb&fileSn=1");
		map.put("쌀",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551b14806b1140d7a2db6f4318b426e36315&fileSn=1");
		map.put("조",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551ba0e3b7031071d0ede151bc27784af402&fileSn=1");
		map.put("찹쌀",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551bbd018ca83eb4e64226d48cc31f6e7a7a&fileSn=1");
		map.put("흑미",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551b73ec400fa8229f37d92294a9d7672c10&fileSn=1");
		map.put("결명자",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551ba5cb02d4654ae82f3788a17d4b584e16&fileSn=1");
		map.put("아욱",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551be8036b95e38e8b3445f91b989dec9562&fileSn=1");
		map.put("살구",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551b5822e1867106b58064cf09ab3219ea2c&fileSn=1");
		map.put("앵두",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551b86caa79045e67b8a6e9327537120c66f&fileSn=1");
		map.put("파인애플",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551b332bb93300f753571defc981bdf35b93&fileSn=1");
		map.put("블루베리",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551bd630784fbdec8998316c9f9accc8319d&fileSn=1");
		map.put("호박씨",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551be4dffe9f18858dc89c63c639c085cf0e&fileSn=1");
		map.put("머위대",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551bc2e968a699040ecd04f62427e8acb989&fileSn=1");
		map.put("대두",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551b40d7bc00db3034264645a1011b457288&fileSn=1");
		map.put("백태",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551b3e92a88e5584646d7b7965417f0f6dcf&fileSn=1");
		map.put("강낭콩",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551be5dc9b516782f05a8919e3cb4b4a8a26&fileSn=1");
		map.put("쥐눈이콩",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551b4b5553506184adf705c94b82f3ecc94c&fileSn=1");
		map.put("강황",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551b0575459c5c27746cf99b9de49b9a96bd&fileSn=1");
		map.put("깻잎",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551b38d8585020d2ab83759f271c6e2ce413&fileSn=1");
		map.put("대파",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551be11a1e000a26f5c03cb46893ee84920a&fileSn=1");
		map.put("상추",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551b435cb9c52124917c9ae8c17846ecf6d4&fileSn=1");
		map.put("샐러리",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551b577a91e27698c24da5f8f3fd90493643&fileSn=1");
		map.put("엉겅퀴",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551b0ef562d4b4c643a6f15f711482a8f97f&fileSn=1");
		map.put("적채",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551b78ea355cead783990413fccef2ff6d49&fileSn=1");
		map.put("총각무",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551b9db4e444dc74a8ce5beddc095027534a&fileSn=1");
		map.put("치커리",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551bcbf3a63aba7b896a15eb6a90c99eeea4&fileSn=1");
		map.put("토란대",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551bbbcd638f113031964d81d7078dd03129&fileSn=1");
		map.put("콩잎",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551bdc95e26d7469a36f09338dd9e6de531a&fileSn=1");
		map.put("수세미",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551bee69820a7bddcab08368f2eeb0a4b63b&fileSn=1");
		map.put("마늘",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551bcdb734fef9af1ba89ab487c31cde0db2&fileSn=1");
		map.put("노루궁뎅이버섯",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551bd10b27fe66d873037b920e9761c5338d&fileSn=1");
		map.put("느타리버섯",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551b2d12ed8b63efa31c0a7b126a89ceecd6&fileSn=1");
		map.put("팽이버섯",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551b63f38a006c3dd9121cc58b6de9547553&fileSn=1");
		map.put("영지버섯",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551ba8b0481563bf1a2a8f29aecc047e7e79&fileSn=1");
		map.put("능이버섯",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551bb5b9282372ab6f6eb337348dc237be9c&fileSn=1");
		map.put("가지버섯",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551bf6803a9313546ded7e7c1b22a05ded27&fileSn=1");
		map.put("상황버섯",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551b743398d4b1dbecbbec9304c8e356e8e8&fileSn=1");
		map.put("숙주나물",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551bc275b4dbf8ae11ab9bf092b068431d8f&fileSn=1");
		map.put("새송이버섯",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551bd986008fec5283c5ef499478bc7a2051&fileSn=1");
		map.put("고추냉이",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551b20e0398d96b746b7a06baeff17a33115&fileSn=1");
		map.put("칡",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551b7b3dc5b0945640475bf04388a99c1bb3&fileSn=1");
		map.put("양송이버섯",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551ba16073628df5b04a64ea81f4cd865f95&fileSn=1");
		map.put("피망",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551b870556ca1bb1645998a52573289c1728&fileSn=1");
		map.put("귀리",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551b91a1c67b37fff1a039cc4c7438014ba4&fileSn=1");
		map.put("참깨",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551b88c3449be3e04d73815d5bd5d95c8351&fileSn=1");
		map.put("머루",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551b1f03582c6856a6b3297667aec3c8ab17&fileSn=1");
		map.put("생강",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551b7583e6207a9c11a6971057cf5fe5e434&fileSn=1");
		map.put("땅콩",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551b460a780249fcb0edeae16a61be611b51&fileSn=1");
		map.put("사과",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551ba35da6b20e40358e0d5165aeb7f1873c&fileSn=1");
		map.put("서리태",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551b60534e8c0badf70d3faf85f1fd20eb1b&fileSn=1");
		map.put("들깨",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551b16fd6467f504d457cc1ce898e65b6512&fileSn=1");
		map.put("밤",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551b90fb84e52380e39893ed8c8ce3cf9b7f&fileSn=1");
		map.put("순무",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551b8c18e479b2c1495e63436c405383e980&fileSn=1");
		map.put("녹두",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551b7052f8a8aeaf5b7416eb0261d4429756&fileSn=1");
		map.put("모과",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551b84e03510105a7c6da13e12da7963a8e4&fileSn=1");
		map.put("메밀",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551b83d25b549cb269bf05f31011bd9233ee&fileSn=1");
		map.put("토란",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551b3451f4346728faaded333f3c40bac32c&fileSn=1");
		map.put("고들빼기",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551b828ad861c0411d343a2959654d94f3ba&fileSn=1");
		map.put("홍고추",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551b5be938cc7b6f5ae1a889dc4d9d3d612e&fileSn=1");
		map.put("호두",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551b359ed5c80faeee6103f5ef396d8cb8b4&fileSn=1");
		map.put("갓",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551bb42ee789e669de1651c82c853768b09b&fileSn=1");
		map.put("표고버섯",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551b55d47beb6fb7c82c4fe676f02ee445f5&fileSn=1");
		map.put("포도",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551b90d0336dfe2ae0d0ead9fba82b4f021f&fileSn=1");
		map.put("팥",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551bb956fcec159b68ad89ee1ded64b1f659&fileSn=1");
		map.put("잣",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551bd4c52baa5043afc72ab9d5eb0af98bbc&fileSn=1");
		map.put("인삼",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551b054ff661617bdfb84d48456bf9185760&fileSn=1");
		map.put("유자",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551b953a7bbc6048d612dee56040a02fda94&fileSn=1");
		map.put("참외",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551bb623e194e8d88f334ea83a51b1e20edf&fileSn=3");
		map.put("은행",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551b3a2fa3592705fff509f1cf9c27bd2c5a&fileSn=1");
		map.put("참나물",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551bce339ddfda2661be6915b4689d6f5dd1&fileSn=1");
		map.put("율무",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551b687767b6bea5aff2fb91abc2c81e5f36&fileSn=1");
		map.put("울금",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551b8698cadf9c33b7b33525d0faccd51c4a&fileSn=1");
		map.put("얼갈이배추",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551b5e4b55e9fe2101aefadf0a23a1f4b71d&fileSn=1");
		map.put("여주(고야)",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551b35f239432e3ebae2837cd3971911a9c0&fileSn=1");
		map.put("오미자",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551b40a32481744ffc00bbebe99fc915207d&fileSn=1");
		map.put("알로에",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551bef17b07e29bd5a651b7f47996169e04e&fileSn=1");
		map.put("야콘",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551bc8fe42da721e36178fccb9ecdef0471d&fileSn=1");
		map.put("석류",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551bbbbc106aaf39d7832c81b8be340b8a4f&fileSn=1");
		map.put("신선초(명일엽)",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551b4cbeb14e37b12256d5d77b999c8d242e&fileSn=1");
		map.put("수박",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551b7257c1595feaf1b6a1c39e11a409fe9f&fileSn=1");
		map.put("배",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551b7f8ecb30911955d420e71a34403a0750&fileSn=1");
		map.put("복숭아",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551b50bd7778f61083b314ac225352645fd7&fileSn=1");
		map.put("배추",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551b81898b4f823e9a873218ed04d46c8e64&fileSn=1");
		map.put("무화과",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551bfb1495723863ffb586a9b133dad3b66f&fileSn=1");
		map.put("방울토마토",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551bdf322e37aaf29203b8f151cf85429e57&fileSn=1");
		map.put("마",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551bad9baa24a3a0ba7c4aded0733f2b9d90&fileSn=1");
		map.put("대추",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551b9be17844eac630c15d66abcfc44b66e8&fileSn=1");
		map.put("무",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551b7a475ef4ba2e929f228e9f91bd8386d8&fileSn=1");
		map.put("멜론",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551b6d33fe869d13d65e2cd4d1284f0b96c0&fileSn=1");
		map.put("단호박",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551b20b7adbf27badf99e7be0490e5f0f5ee&fileSn=1");
		map.put("파프리카",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551b3d689f7c046f261cbdd7fe459784b88b&fileSn=1");
		map.put("고구마",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551bd5369a4111b65ebc980950cca5da4cb4&fileSn=1");
		map.put("자두",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551bea21cade2ab816b3a60196168b2c00af&fileSn=1");
		map.put("늙은 호박",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551b01d411f4be02f542cf4753e86702a7e9&fileSn=1");
		map.put("열무",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551b3b706d0fa66f05f81eba53fe3f165206&fileSn=1");
		map.put("용과",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551bae2cf8a5906ec7d93179c079fe5708ac&fileSn=1");
		map.put("애플망고",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551bcf8a01858d65758b5933aac20bb52067&fileSn=1");
		map.put("옥수수",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551bd15039133baf346d7f777a120749e30c&fileSn=1");
		map.put("오이",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551b0b32d6899e091f9240ca38d116dc2b39&fileSn=1");
		map.put("산딸기",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551bfb034351c187a729377eab03130d47f6&fileSn=1");
		map.put("부추",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551bfbfbcc30a34a6e9511f2f5a728a0938a&fileSn=1");
		map.put("양파",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551bccfe792f713b844f74e3468f2ddf64e8&fileSn=1");
		map.put("감",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551b755c16115c1730db1f38265b04faf248&fileSn=1");
		map.put("애호박",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551b101f07273f2eb6891d386b0c727f6560&fileSn=1");
		map.put("복분자",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551b621397f45e6055b4ee7c1146921aab6f&fileSn=1");
		map.put("아로니아",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551bfc82dab1b4eaf9b46818aa242d416b28&fileSn=1");
		map.put("비름나물",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551bab5a0b8198a0e3b5373a312ee2069d5d&fileSn=1");
		map.put("매실",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551ba0937baf74c9a0a972fc5c554a722002&fileSn=1");
		map.put("도라지",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551bfb6c63d8fa64f66bb82da1ae2b28d337&fileSn=1");
		map.put("꽈리고추",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551b6b61d325526689bf93a7560d34224b6e&fileSn=1");
		map.put("감자",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551ba37efcad4f37830022ee63cb134797c1&fileSn=1");
		map.put("가지",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551bc33a5f36f74854d274291327aac379a2&fileSn=1");
		map.put("근대",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551b17bc201a47ba9259c65d27640b3b6445&fileSn=1");
		map.put("곤드레",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551ba91899886ec7bf0aa823c1ac597953c6&fileSn=1");
		map.put("호박잎",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551b2abc8909b5ec8d7982e16b1680c33280&fileSn=1");
		map.put("하귤",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551b3a13fea37bae61efbef1bd3f2b4dc9c8&fileSn=1");
		map.put("취나물",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551b1253ca6a06f1d7e44a9a2697b1ea2658&fileSn=1");
		map.put("완두콩",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551b85380e4a5167e06c4542e83d8c38d867&fileSn=1");
		map.put("쪽파",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551b860ba846375bdfe2809454d02d4306e5&fileSn=1");
		map.put("아스파라거스",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551b74fb902364885991fb818e985ae88b23&fileSn=1");
		map.put("죽순",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551bea2f7878922d32f36d56809b3c09edda&fileSn=1");
		map.put("씀바귀",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551b078a6edd55c14687e52a7ffb173c9cee&fileSn=1");
		map.put("오디",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551b4ecaf8f19ed266298b5528a771e8db17&fileSn=1");
		map.put("쑥",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551bc67a69efc14df528a80c288a16434caa&fileSn=1");
		map.put("양배추",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551b7a91b8b9f6e5a375d9d841c07e246416&fileSn=1");
		map.put("머위",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551b841e9a488816606585db416303e9e1c8&fileSn=1");
		map.put("쑥갓",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551bc7c2218c8e6c067dac31f28a8ffb038f&fileSn=1");
		map.put("두릅",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551b0046d90dc49f036e867db7209469ef71&fileSn=1");
		map.put("방풍나물",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551b6e081230bf7a8d14131d6cb1ebf1ef66&fileSn=1");
		map.put("더덕",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551bc68d52a96af9fea7f77ad4ffd08e4c18&fileSn=1");
		map.put("마늘종",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551b3d3222a8f95da38d94c747d55225656e&fileSn=1");
		map.put("냉이",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551b862bad768652c13d0f7e7746364c7ab3&fileSn=1");
		map.put("고구마순",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551b44e35b50879e24c71f71259ad2de8a35&fileSn=1");
		map.put("껍질콩(그린빈)",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551bb60b513baddbe7cae1d0d42b506bc431&fileSn=1");
		map.put("세발나물",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551bba35682eeb9e26976dd62618d3b395da&fileSn=1");
		map.put("비트",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551bc4e9919a1368ffed529d709b18443c6c&fileSn=1");
		map.put("고사리",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551b4a04b072a9bcbc4faa942d8ee61c7ac7&fileSn=1");
		map.put("유채나물",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551b691024d0e07f31d3aa856014b633e1d6&fileSn=1");
		map.put("돌나물",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551bcdead639366da313c15c1a13e7b6aceb&fileSn=1");
		map.put("미나리",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551bcb4b00123c29dc95606167b8642e629a&fileSn=1");
		map.put("달래",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551b521ed0531ad3eb804729a5759b4c23be&fileSn=1");
		map.put("딸기",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551bd29f4ba05177c56f3c6d7626ecf95274&fileSn=1");
		map.put("천혜향",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551b3e561ab04edcc80c47bdce547f5f03bf&fileSn=1");
		map.put("한라봉",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551ba0c871f62d4b574fe42fe9d4d9b689b5&fileSn=1");
		map.put("연근",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551ba8a45e2dab2d187c38fcbced9e0e7a7b&fileSn=1");
		map.put("레몬",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551b2405e9cfa0dc45ffd724f094cddb7742&fileSn=1");
		map.put("시금치",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551b2d855dd883da14c9b6723a7f4da88d20&fileSn=1");
		map.put("우엉",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551bcef47050c4f85c6fd870a32adc51c0b3&fileSn=1");
		map.put("감귤",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551b465ff00f0f6d8d95b2ba1285277024d9&fileSn=1");
		map.put("당근",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551bb36d8258c28328668afcdf187fb515c8&fileSn=1");
		map.put("브로콜리",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551bc11c37dcd6aa9864584a35c9936090a0&fileSn=1");
		map.put("콜라비",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551b4eb172ae7b1dcbffb79573f27b54c2aa&fileSn=1");
		map.put("콜리플라워",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551b6e2b2538718a9cd3a85661b718490dc6&fileSn=1");
		map.put("키위",
				"https://www.foodnuri.go.kr/cmmn/file/getImage.do?atchFileId=94ae4a12d4c5d4b9a91f05709d97551b01d7bc7a622c4ab59ab6b4e6b5db86e5&fileSn=1");
		return map;
	}
}

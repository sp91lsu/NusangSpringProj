package com.mycom.blog.service.testData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import com.mycom.blog.dto.enumtype.Price_Coin;

public class DataList {
	Random r = new Random();

//유저
//	아이디
	String engNameList_raw = "Aaliyah," + "Abby," + "Abigail," + "Ada," + "Adalee," + "Adaline," + "Adalyn,"
			+ "Adalynn," + "Addilyn," + "Addilynn," + "Addison," + "Addisyn," + "Addyson," + "Adelaide," + "Adele,"
			+ "Adelina," + "Adeline," + "Adelyn," + "Adelynn," + "Adley," + "Adriana," + "Adrianna," + "Adrienne,"
			+ "Ailani," + "Aileen," + "Ainsley," + "Aisha," + "Aislinn," + "Aitana," + "Aiyana," + "Alaia," + "Alaina,"
			+ "Alana," + "Alani," + "Alanna," + "Alannah," + "Alaya," + "Alayah," + "Alayna," + "Aleah," + "Aleena,"
			+ "Alejandra," + "Alena," + "Alessandra," + "Alessia," + "Alexa," + "Alexandra," + "Alexandria," + "Alexia,"
			+ "Alexis," + "Alia," + "Aliana," + "Alianna," + "Alice," + "Alicia," + "Alina," + "Alisha," + "Alison,"
			+ "Alisson," + "Alivia," + "Aliya," + "Aliyah," + "Aliza," + "Allie," + "Allison," + "Allyson," + "Alma,"
			+ "Alondra," + "Alora," + "Alyson," + "Alyssa," + "Amaia," + "Amalia," + "Amanda," + "Amani," + "Amara,"
			+ "Amari," + "Amaris," + "Amaya," + "Amayah," + "Amber," + "Amelia," + "Amelie," + "Amia," + "Amina,"
			+ "Amira," + "Amirah," + "Amiya," + "Amiyah," + "Amora," + "Amy," + "Ana," + "Anahi," + "Anais," + "Analia,"
			+ "Anastasia," + "Anaya," + "Andi," + "Andrea," + "Angel," + "Angela," + "Angelica," + "Angelina,"
			+ "Angie," + "Anika," + "Aniya," + "Aniyah," + "Ann," + "Anna," + "Annabel," + "Annabella," + "Annabelle,"
			+ "Annalee," + "Annalise," + "Anne," + "Annie," + "Annika," + "Ansley," + "Antonella," + "Anya," + "April,"
			+ "Arabella," + "Arden," + "Arely," + "Ari," + "Aria," + "Ariadne," + "Ariah," + "Ariana," + "Arianna,"
			+ "Ariel," + "Ariella," + "Arielle," + "Ariya," + "Ariyah," + "Arlette," + "Armani," + "Arya," + "Ashley,"
			+ "Ashlyn," + "Ashlynn," + "Aspen," + "Astrid," + "Athena," + "Aubree," + "Aubrey," + "Aubrie,"
			+ "Aubriella," + "Aubrielle," + "Audrey," + "August," + "Aurelia," + "Aurora," + "Austyn," + "Autumn,"
			+ "Ava," + "Avah," + "Avalyn," + "Avalynn," + "Averi," + "Averie," + "Avery," + "Aviana," + "Avianna,"
			+ "Aya," + "Ayla," + "Ayleen," + "Aylin," + "Azalea," + "Azaria," + "Azariah," + "Bailee," + "Bailey,"
			+ "Barbara," + "Baylee," + "Beatrice," + "Belen," + "Bella," + "Bellamy," + "Belle," + "Berkley,"
			+ "Bethany," + "Bexley," + "Bianca," + "Blair," + "Blaire," + "Blake," + "Blakely," + "Bonnie," + "Braelyn,"
			+ "Braelynn," + "Braylee," + "Bria," + "Briana," + "Brianna," + "Briar," + "Bridget," + "Briella,"
			+ "Brielle," + "Brinley," + "Bristol," + "Brittany," + "Brooke," + "Brooklyn," + "Brooklynn," + "Brylee,"
			+ "Brynlee," + "Brynleigh," + "Brynn," + "Cadence," + "Cali," + "Callie," + "Calliope," + "Cameron,"
			+ "Camila," + "Camilla," + "Camille," + "Camryn," + "Cara," + "Carly," + "Carmen," + "Carolina,"
			+ "Caroline," + "Carolyn," + "Carter," + "Casey," + "Cassandra," + "Cassidy," + "Cataleya," + "Catalina,"
			+ "Catherine," + "Cecelia," + "Cecilia," + "Celeste," + "Celia," + "Celine," + "Chana," + "Chanel,"
			+ "Charlee," + "Charleigh," + "Charley," + "Charli," + "Charlie," + "Charlotte," + "Chaya," + "Chelsea,"
			+ "Cheyenne," + "Chloe," + "Christina," + "Christine," + "Claire," + "Clara," + "Clare," + "Clarissa,"
			+ "Claudia," + "Clementine," + "Colette," + "Collins," + "Cora," + "Coraline," + "Corinne," + "Crystal,"
			+ "Cynthia," + "Dahlia," + "Daisy," + "Dakota," + "Dalary," + "Daleyza," + "Dallas," + "Dana," + "Dani,"
			+ "Daniela," + "Daniella," + "Danielle," + "Danna," + "Daphne," + "Davina," + "Dayana," + "Deborah,"
			+ "Delaney," + "Delilah," + "Della," + "Demi," + "Destiny," + "Diana," + "Dior," + "Dorothy," + "Dream,"
			+ "Dulce," + "Dylan," + "Eden," + "Edith," + "Egypt," + "Eileen," + "Elaina," + "Elaine," + "Eleanor,"
			+ "Elena," + "Eliana," + "Elianna," + "Elina," + "Elisa," + "Elisabeth," + "Elise," + "Eliza,"
			+ "Elizabeth," + "Ella," + "Elle," + "Ellen," + "Elliana," + "Ellianna," + "Ellie," + "Elliot," + "Elliott,"
			+ "Ellis," + "Ellison," + "Eloise," + "Elora," + "Elsa," + "Elsie," + "Elyse," + "Ember," + "Emberly,"
			+ "Emelia," + "Emely," + "Emerie," + "Emerson," + "Emersyn," + "Emery," + "Emilee," + "Emilia," + "Emily,"
			+ "Emma," + "Emmaline," + "Emmalyn," + "Emmalynn," + "Emmarie," + "Emmeline," + "Emmie," + "Emmy,"
			+ "Emory," + "Ensley," + "Erica," + "Erika," + "Erin," + "Esme," + "Esmeralda," + "Esperanza," + "Estella,"
			+ "Estelle," + "Esther," + "Estrella," + "Etta," + "Eva," + "Evangeline," + "Eve," + "Evelyn," + "Evelynn,"
			+ "Everlee," + "Everleigh," + "Everly," + "Evie," + "Ezra," + "Faith," + "Fatima," + "Faye," + "Felicity,"
			+ "Fernanda," + "Finley," + "Fiona," + "Florence," + "Frances," + "Francesca," + "Frankie," + "Freya,"
			+ "Frida," + "Gabriela," + "Gabriella," + "Gabrielle," + "Galilea," + "Gemma," + "Genesis," + "Genevieve,"
			+ "Georgia," + "Gia," + "Giana," + "Gianna," + "Giavanna," + "Giovanna," + "Giselle," + "Giuliana,"
			+ "Gloria," + "Grace," + "Gracelyn," + "Gracelynn," + "Gracie," + "Greta," + "Guadalupe," + "Gwen,"
			+ "Gwendolyn," + "Hadassah," + "Hadlee," + "Hadleigh," + "Hadley," + "Hailee," + "Hailey," + "Haisley,"
			+ "Haley," + "Halle," + "Hallie," + "Hana," + "Hanna," + "Hannah," + "Harlee," + "Harleigh," + "Harley,"
			+ "Harlow," + "Harmoni," + "Harmony," + "Harper," + "Hattie," + "Haven," + "Hayden," + "Haylee," + "Hayley,"
			+ "Hazel," + "Heaven," + "Heavenly," + "Heidi," + "Helen," + "Helena," + "Henley," + "Holland," + "Holly,"
			+ "Hope," + "Hunter," + "Iliana," + "Imani," + "India," + "Ingrid," + "Irene," + "Iris," + "Isabel,"
			+ "Isabela," + "Isabella," + "Isabelle," + "Isla," + "Itzayana," + "Itzel," + "Ivanna," + "Ivory," + "Ivy,"
			+ "Izabella," + "Jacqueline," + "Jada," + "Jade," + "Jaelyn," + "Jaelynn," + "Jaliyah," + "Jamie," + "Jana,"
			+ "Jane," + "Janelle," + "Janessa," + "Janiyah," + "Jasmine," + "Jaycee," + "Jayda," + "Jayde," + "Jayden,"
			+ "Jayla," + "Jaylah," + "Jaylee," + "Jayleen," + "Jaylene," + "Jazlyn," + "Jazlynn," + "Jazmin,"
			+ "Jazmine," + "Jemma," + "Jenesis," + "Jenna," + "Jennifer," + "Jessica," + "Jessie," + "Jewel,"
			+ "Jillian," + "Jimena," + "Joanna," + "Jocelyn," + "Joelle," + "Johanna," + "Jolene," + "Jolie,"
			+ "Jordan," + "Jordyn," + "Joselyn," + "Josephine," + "Josie," + "Journee," + "Journey," + "Journi,"
			+ "Joy," + "Joyce," + "Judith," + "Julia," + "Juliana," + "Julianna," + "Julie," + "Juliet," + "Julieta,"
			+ "Juliette," + "Julissa," + "June," + "Juniper," + "Jurnee," + "Justice," + "Kadence," + "Kaelyn," + "Kai,"
			+ "Kaia," + "Kailani," + "Kailey," + "Kailyn," + "Kairi," + "Kaitlyn," + "Kaiya," + "Kalani," + "Kali,"
			+ "Kaliyah," + "Kallie," + "Kamila," + "Kamilah," + "Kamiyah," + "Kamryn," + "Kara," + "Karen," + "Karina,"
			+ "Karla," + "Karlee," + "Karsyn," + "Karter," + "Kassidy," + "Kataleya," + "Katalina," + "Kate,"
			+ "Katelyn," + "Katherine," + "Kathleen," + "Kathryn," + "Katie," + "Kaydence," + "Kayla," + "Kaylani,"
			+ "Kaylee," + "Kayleigh," + "Kaylie," + "Kaylin," + "Kehlani," + "Keilani," + "Keily," + "Keira," + "Kelly,"
			+ "Kelsey," + "Kendall," + "Kendra," + "Kenia," + "Kenley," + "Kenna," + "Kennedi," + "Kennedy,"
			+ "Kensley," + "Kenzie," + "Keyla," + "Khaleesi," + "Khloe," + "Kiana," + "Kiara," + "Kiera," + "Kimber,"
			+ "Kimberly," + "Kimora," + "Kinley," + "Kinslee," + "Kinsley," + "Kira," + "Kora," + "Kori," + "Kyla,"
			+ "Kylee," + "Kyleigh," + "Kylie," + "Kynlee," + "Kyra," + "Lacey," + "Laila," + "Lailah," + "Lainey,"
			+ "Lana," + "Landry," + "Laney," + "Lara," + "Laura," + "Laurel," + "Lauren," + "Lauryn," + "Layla,"
			+ "Laylah," + "Lea," + "Leah," + "Leanna," + "Legacy," + "Leia," + "Leighton," + "Leila," + "Leilani,"
			+ "Lena," + "Lennon," + "Lennox," + "Leona," + "Leslie," + "Lexi," + "Lexie," + "Leyla," + "Lia," + "Liana,"
			+ "Liberty," + "Lila," + "Lilah," + "Lilian," + "Liliana," + "Lilianna," + "Lilith," + "Lillian,"
			+ "Lilliana," + "Lillianna," + "Lillie," + "Lilly," + "Lily," + "Lilyana," + "Lina," + "Linda," + "Lindsey,"
			+ "Lisa," + "Liv," + "Livia," + "Logan," + "Lola," + "London," + "Londyn," + "Lorelai," + "Lorelei,"
			+ "Louisa," + "Louise," + "Lucia," + "Luciana," + "Lucille," + "Lucy," + "Luella," + "Luna," + "Lyanna,"
			+ "Lydia," + "Lyla," + "Lylah," + "Lyra," + "Lyric," + "Mabel," + "Maci," + "Macie," + "Mackenzie,"
			+ "Macy," + "Madalyn," + "Madalynn," + "Maddison," + "Madeleine," + "Madeline," + "Madelyn," + "Madelynn,"
			+ "Madilyn," + "Madilynn," + "Madison," + "Madisyn," + "Mae," + "Maeve," + "Maggie," + "Magnolia," + "Maia,"
			+ "Maisie," + "Makayla," + "Makenna," + "Makenzie," + "Malani," + "Malaya," + "Malaysia," + "Maleah,"
			+ "Malia," + "Maliah," + "Maliyah," + "Mallory," + "Mara," + "Maren," + "Margaret," + "Margo," + "Margot,"
			+ "Maria," + "Mariah," + "Mariam," + "Mariana," + "Marianna," + "Marie," + "Marilyn," + "Marina,"
			+ "Marisol," + "Marissa," + "Marlee," + "Marleigh," + "Marley," + "Martha," + "Mary," + "Maryam,"
			+ "Matilda," + "Mavis," + "Maxine," + "Maya," + "Mckenna," + "Mckenzie," + "Mckinley," + "Meadow,"
			+ "Megan," + "Meghan," + "Meilani," + "Melanie," + "Melany," + "Melina," + "Melissa," + "Melody," + "Mercy,"
			+ "Meredith," + "Mia," + "Miah," + "Micah," + "Michaela," + "Michelle," + "Mikaela," + "Mikayla," + "Mila,"
			+ "Milan," + "Milana," + "Milani," + "Milena," + "Miley," + "Millie," + "Mina," + "Mira," + "Miracle,"
			+ "Miranda," + "Miriam," + "Molly," + "Monica," + "Monroe," + "Morgan," + "Mya," + "Myah," + "Myla,"
			+ "Mylah," + "Myra," + "Nadia," + "Nala," + "Nalani," + "Nancy," + "Naomi," + "Natalia," + "Natalie,"
			+ "Nataly," + "Natasha," + "Nathalie," + "Naya," + "Nayeli," + "Nevaeh," + "Nia," + "Nicole," + "Nina,"
			+ "Noa," + "Noelle," + "Noemi," + "Nola," + "Noor," + "Nora," + "Norah," + "Nova," + "Novah," + "Novalee,"
			+ "Nyla," + "Nylah," + "Oaklee," + "Oakley," + "Oaklyn," + "Oaklynn," + "Octavia," + "Olive," + "Olivia,"
			+ "Opal," + "Ophelia," + "Paige," + "Paislee," + "Paisleigh," + "Paisley," + "Palmer," + "Paloma,"
			+ "Paola," + "Paris," + "Parker," + "Patricia," + "Paula," + "Paulina," + "Payton," + "Pearl," + "Penelope,"
			+ "Penny," + "Perla," + "Peyton," + "Phoebe," + "Phoenix," + "Piper," + "Poppy," + "Presley," + "Princess,"
			+ "Priscilla," + "Promise," + "Queen," + "Quinn," + "Rachel," + "Raegan," + "Raelyn," + "Raelynn,"
			+ "Raina," + "Ramona," + "Raquel," + "Raven," + "Rayna," + "Rayne," + "Reagan," + "Rebecca," + "Rebekah,"
			+ "Reese," + "Regina," + "Reign," + "Reina," + "Remi," + "Remington," + "Remy," + "Renata," + "Reyna,"
			+ "Rhea," + "Riley," + "River," + "Rivka," + "Robin," + "Romina," + "Rory," + "Rosa," + "Rosalee,"
			+ "Rosalie," + "Rosalyn," + "Rose," + "Roselyn," + "Rosemary," + "Rosie," + "Rowan," + "Royal," + "Royalty,"
			+ "Ruby," + "Ruth," + "Ryan," + "Ryann," + "Rylan," + "Rylee," + "Ryleigh," + "Rylie," + "Sabrina,"
			+ "Sadie," + "Sage," + "Saige," + "Salma," + "Samantha," + "Samara," + "Samira," + "Sandra," + "Saniyah,"
			+ "Saoirse," + "Sara," + "Sarah," + "Sarai," + "Sariah," + "Sariyah," + "Sasha," + "Savanna," + "Savannah,"
			+ "Sawyer," + "Saylor," + "Scarlet," + "Scarlett," + "Scarlette," + "Scout," + "Selah," + "Selena,"
			+ "Selene," + "Serena," + "Serenity," + "Shelby," + "Shiloh," + "Siena," + "Sienna," + "Sierra," + "Simone,"
			+ "Sky," + "Skye," + "Skyla," + "Skylar," + "Skyler," + "Sloan," + "Sloane," + "Sofia," + "Sophia,"
			+ "Sophie," + "Stella," + "Stephanie," + "Stevie," + "Summer," + "Sunny," + "Sutton," + "Sydney,"
			+ "Sylvia," + "Sylvie," + "Talia," + "Taliyah," + "Tatiana," + "Tatum," + "Taylor," + "Teagan," + "Tenley,"
			+ "Teresa," + "Tessa," + "Thalia," + "Thea," + "Tiana," + "Tiffany," + "Tinley," + "Tinsley," + "Tori,"
			+ "Treasure," + "Trinity," + "Vada," + "Valentina," + "Valeria," + "Valerie," + "Valery," + "Vanessa,"
			+ "Veda," + "Vera," + "Veronica," + "Victoria," + "Vienna," + "Violet," + "Violeta," + "Virginia,"
			+ "Vivian," + "Viviana," + "Vivienne," + "Waverly," + "Wendy," + "Whitley," + "Whitney," + "Willa,"
			+ "Willow," + "Winter," + "Wren," + "Wynter," + "Ximena," + "Xiomara," + "Yamileth," + "Yara," + "Yareli,"
			+ "Yaretzi," + "Zahra," + "Zainab," + "Zaniyah," + "Zara," + "Zaria," + "Zariah," + "Zariyah," + "Zaylee,"
			+ "Zelda," + "Zhavia," + "Zoe," + "Zoey," + "Zoie," + "Zola," + "Zora," + "Zuri";

	String[] engNameList() {
		String[] result = engNameList_raw.split(",");
		return result;
	}

//	성
	String firstNames = "김,이,박,조,최,정,손,강,임,신,장,윤,오,권,전,유,한,서,안,황,송,홍,양,고,문,배,백,류,허,노,남,심,주,구,하,곽,성,차,민,진,우,엄,나,지,변,방,원,채,천,공";

	String[] firstNameList() {
		String[] result = firstNames.split(",");
		return result;
	}

//	이름
	// 남자
	String secondNames_M = "민준,지훈,현우,주원,시우,우진,지후,현준,서준,도현,지호,승현,진우,도윤,건우,지원,준영,준호,동현,선우,성민,정우,민재,예준,준혁,수현,현수,정훈,민수,준우,연우,민규,준서,정민,지환,성현,재현,민우,서진,지우,민호,시후,정현,재민,민성,승우,상현,재원,윤호,승민";

	String[] secondNameList_M() {
		String[] result = secondNames_M.split(",");
		return result;
	}

	// 여자
	String secondNames_W = "유진,지영,지은,지원,수진,지혜,서연,수연,민정,민지,지현,지연,혜진,서현,지윤,현정,서윤,수현,소영,지민,수정,은정,은지,소연,민서,현주,지우,민주,은영,민경,혜원,수빈,선영,서영,은주,채원,수아,수민,유정,지수,정은,주희,예진,윤정,윤서,주연,지아,미경,지유,은경";

	String[] secondNameList_W() {
		String[] result = secondNames_W.split(",");
		return result;
	}

//	닉네임
	String nicNames = "아바타,닉네임,우유,커피,모카,초코,브라우니,댕댕이,삐삐,뚠뚠,강냉이,저승사자,잠만보,안드로메다,프라하,고인물,갑분싸,인싸,존잘러,곰스,바운스,뚱이,복서,해충박멸,디스코드,후라이팬,존버,꿀꿀이,나쵸,너구리,아몬드,땅콩,치즈,호떡,만두,대파,부엉이,프로,록맨,호구맨,레이저,고독,저격,달님,더힐,놀고있네,프리미어,메이트,바른마음,백구,잇츠,앗메리카노,고고고고,오렌지피클,땡땡이,라이트,월계수,테슬라,실루엣,시계,레전드,결정,시즌,뉴비,버드,열차,공포,다이,전기,윈디,감옥,빌런,데빌,파이어,워터,바이,호시탐탐,레이븐,세트,순대,물범,레이드,베테랑,팔라딘,극비,다이어트,풀피,레나,야채,푸른점,푸른곰팡이,초록괴물,독수리,시사회,청둥오리,아이스크림,대나무,바이올린,바이올렛,인터스텔라";

	String[] nicNameList() {
		String[] result = nicNames.split(",");
		return result;
	}

//	나이 20~40
	int[] ageCurve = { 3, 3, 3, 3, 4, 5, 6, 5, 4, 4, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1 };

	int[] ageList() {
		ArrayList<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i < ageCurve.length; i++) {
			for (int j = 0; j < ageCurve[i]; j++) {
				result.add(i);
			}
		}
		Integer[] resultI = (Integer[]) result.toArray(new Integer[result.size()]);
		return Arrays.stream(resultI).mapToInt(Integer::intValue).toArray();
	}

//  성별 ex) 남 61.7% 여 38.3%
	String gender(double mPer) {
		String result = null;

		double temp = 0;
		temp = (double) r.nextInt(10000);
		result = temp / 100 >= 0 && temp / 100 < mPer ? "남" : "여";
		return result;
	}

//	이메일 @ 뒤
	String email() {
		String[] emailList = "naver.com,daum.net,nate.com,gmail.com,hotmail.com".split(",");
		int choice = r.nextInt(emailList.length);
		return emailList[choice];
	}

//	결제금액 랜덤
	Price_Coin randPrice_Coin() {
		int random = r.nextInt(Price_Coin.values().length);
		Price_Coin pc = Price_Coin.values()[random];
		return pc;
	}

	public static void main(String[] args) {
		DataList s = new DataList();
//		System.out.println(Arrays.toString(s.nicNameList()));
//		System.out.println(s.nicNameList().length);
		for (Price_Coin pc : Price_Coin.values()) {
			System.out.println(pc.getCoin());
			System.out.println(pc.getPrice());
		}
	}
}

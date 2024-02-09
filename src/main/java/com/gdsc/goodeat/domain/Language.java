package com.gdsc.goodeat.domain;

import static com.gdsc.goodeat.exception.LanguageExceptionType.LANGUAGE_NOT_FOUND;

import com.gdsc.goodeat.exception.LanguageException;
import java.util.Arrays;

public enum Language {

  AFRIKAANS("Afrikaans", "af"),
  ALBANIAN("Albanian", "sq"),
  AMHARIC("Amharic", "am"),
  ARABIC("Arabic", "ar"),
  ARMENIAN("Armenian", "hy"),
  ASSAMESE("Assamese", "as"),
  AYMARA("Aymara", "ay"),
  AZERBAIJANI("Azerbaijani", "az"),
  BAMBARA("Bambara", "bm"),
  BASQUE("Basque", "eu"),
  BELARUSIAN("Belarusian", "be"),
  BENGALI("Bengali", "bn"),
  BHOJPURI("Bhojpuri", "bho"),
  BOSNIAN("Bosnian", "bs"),
  BULGARIAN("Bulgarian", "bg"),
  CATALAN("Catalan", "ca"),
  CEBUANO("Cebuano", "ceb"),
  CHINESE_SIMPLIFIED("Chinese (Simplified)", "zh-CN or zh (BCP-47)"),
  CHINESE_TRADITIONAL("Chinese (Traditional)", "zh-TW (BCP-47)"),
  CORSICAN("Corsican", "co"),
  CROATIAN("Croatian", "hr"),
  CZECH("Czech", "cs"),
  DANISH("Danish", "da"),
  DHIVEHI("Dhivehi", "dv"),
  DOGRI("Dogri", "doi"),
  DUTCH("Dutch", "nl"),
  ENGLISH("English", "en"),
  ESPERANTO("Esperanto", "eo"),
  ESTONIAN("Estonian", "et"),
  EWE("Ewe", "ee"),
  FILIPINO_TAGLAOG("Filipino (Tagalog)", "fil"),
  FINNISH("Finnish", "fi"),
  FRENCH("French", "fr"),
  FRISIAN("Frisian", "fy"),
  GALICIAN("Galician", "gl"),
  GEORGIAN("Georgian", "ka"),
  GERMAN("German", "de"),
  GREEK("Greek", "el"),
  GUARANI("Guarani", "gn"),
  GUJARATI("Gujarati", "gu"),
  HAITIAN_CREOLE("Haitian Creole", "ht"),
  HAUSA("Hausa", "ha"),
  HAWAIIAN("Hawaiian", "haw"),
  HEBREW("Hebrew", "he or iw"),
  HINDI("Hindi", "hi"),
  HMONG("Hmong", "hmn"),
  HUNGARIAN("Hungarian", "hu"),
  ICELANDIC("Icelandic", "is"),
  IGBO("Igbo", "ig"),
  ILOCANO("Ilocano", "ilo"),
  INDONESIAN("Indonesian", "id"),
  IRISH("Irish", "ga"),
  ITALIAN("Italian", "it"),
  JAPANESE("Japanese", "ja"),
  JAVANESE("Javanese", "jv or jw"),
  KANNADA("Kannada", "kn"),
  KAZAKH("Kazakh", "kk"),
  KHMER("Khmer", "km"),
  KINYARWANDA("Kinyarwanda", "rw"),
  KONKANI("Konkani", "gom"),
  KOREAN("Korean", "ko"),
  KRIO("Krio", "kri"),
  KURDISH("Kurdish", "ku"),
  KURDISH_SORANI("Kurdish (Sorani)", "ckb"),
  KYRGYZ("Kyrgyz", "ky"),
  LAO("Lao", "lo"),
  LATIN("Latin", "la"),
  LATVIAN("Latvian", "lv"),
  LINGALA("Lingala", "ln"),
  LITHUANIAN("Lithuanian", "lt"),
  LUGANDA("Luganda", "lg"),
  LUXEMBOURGISH("Luxembourgish", "lb"),
  MACEDONIAN("Macedonian", "mk"),
  MAITHILI("Maithili", "mai"),
  MALAGASY("Malagasy", "mg"),
  MALAY("Malay", "ms"),
  MALAYALAM("Malayalam", "ml"),
  MALTESE("Maltese", "mt"),
  MAORI("Maori", "mi"),
  MARATHI("Marathi", "mr"),
  MEITEILON_MANIPURI("Meiteilon (Manipuri)", "mni-Mtei"),
  MIZO("Mizo", "lus"),
  MONGOLIAN("Mongolian", "mn"),
  MYANMAR_BURMESE("Myanmar (Burmese)", "my"),
  NEPALI("Nepali", "ne"),
  NORWEGIAN("Norwegian", "no"),
  NYANJA_CHICHEWA("Nyanja (Chichewa)", "ny"),
  ODIA_ORIYA("Odia (Oriya)", "or"),
  OROMO("Oromo", "om"),
  PASHTO("Pashto", "ps"),
  PERSIAN("Persian", "fa"),
  POLISH("Polish", "pl"),
  PORTUGUESE_PORTUGAL_BRAZIL("Portuguese (Portugal, Brazil)", "pt"),
  PUNJABI("Punjabi", "pa"),
  QUECHUA("Quechua", "qu"),
  ROMANIAN("Romanian", "ro"),
  RUSSIAN("Russian", "ru"),
  SAMOAN("Samoan", "sm"),
  SANSKRIT("Sanskrit", "sa"),
  SCOTS_GAELIC("Scots Gaelic", "gd"),
  SEPEDI("Sepedi", "nso"),
  SERBIAN("Serbian", "sr"),
  SESOTHO("Sesotho", "st"),
  SHONA("Shona", "sn"),
  SINDHI("Sindhi", "sd"),
  SINHALA_SINHALESE("Sinhala (Sinhalese)", "si"),
  SLOVAK("Slovak", "sk"),
  SLOVENIAN("Slovenian", "sl"),
  SOMALI("Somali", "so"),
  SPANISH("Spanish", "es"),
  SUNDANESE("Sundanese", "su"),
  SWAHILI("Swahili", "sw"),
  SWEDISH("Swedish", "sv"),
  TAGALOG_FILIPINO("Tagalog (Filipino)", "tl"),
  TAJIK("Tajik", "tg"),
  TAMIL("Tamil", "ta"),
  TATAR("Tatar", "tt"),
  TELUGU("Telugu", "te"),
  THAI("Thai", "th"),
  TIGRINYA("Tigrinya", "ti"),
  TSONGA("Tsonga", "ts"),
  TURKISH("Turkish", "tr"),
  TURKMEN("Turkmen", "tk"),
  TWI_AKAN("Twi (Akan)", "ak"),
  UKRAINIAN("Ukrainian", "uk"),
  URDU("Urdu", "ur"),
  UYGHUR("Uyghur", "ug"),
  UZBEK("Uzbek", "uz"),
  VIETNAMESE("Vietnamese", "vi"),
  WELSH("Welsh", "cy"),
  XHOSA("Xhosa", "xh"),
  YIDDISH("Yiddish", "yi"),
  YORUBA("Yoruba", "yo"),
  ZULU("Zulu", "zu");

  private final String languageName;
  private final String ISO639Code;

  Language(final String languageName, final String ISO639Code) {
    this.languageName = languageName;
    this.ISO639Code = ISO639Code;
  }

  public static Language fromLanguageName(final String languageName) {
    return Arrays.stream(values())
        .filter(lan -> lan.languageName.equals(languageName))
        .findAny()
        .orElseThrow(() -> new LanguageException(LANGUAGE_NOT_FOUND));
  }

  public static Language fromISOCode(final String code) {
    return Arrays.stream(values())
        .filter(lan -> lan.ISO639Code.equals(code))
        .findAny()
        .orElseThrow(() -> new LanguageException(LANGUAGE_NOT_FOUND));
  }

  public String getLanguageName() {
    return languageName;
  }

  public String getISO639Code() {
    return ISO639Code;
  }
}

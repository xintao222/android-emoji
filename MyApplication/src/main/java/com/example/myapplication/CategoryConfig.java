package com.example.myapplication;

import android.content.res.Resources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ludwang on 14-1-18.
 */
public class CategoryConfig {

    public static List<Map<String, String>> get2LineMap(Resources resources) {
        List<Map<String, String>> lines = new ArrayList<Map<String, String>>();
        for (EmojiCategory category : EmojiCategory.values()) {
            Map<String, String> line = new HashMap<String, String>();
            line.put("First Line", resources.getString(category.nameResourceId));
            line.put("Second Line", category.description);
            lines.add(line);
        }

        return lines;
    }

    public static String[] getListResourceId(String category) {
        return EmojiCategory.toEmojiCategory(category).resources;
    }

    public static int getNameResourceId(String category) {
        return EmojiCategory.toEmojiCategory(category).nameResourceId;
    }

    private static String[] smileList = {
            "d(d＇∀＇)",
            "d(`･∀･)b",
            "(*´∀`)~♥",
            "σ(`∀´)σ",
            "(〃∀〃)",
            "(^_っ^)",
            "(｡O∀O｡)",
            "ヽ(✿ﾟ▽ﾟ)ノ",
            "(σ′▽‵)′▽‵)σ",
            "(((o(*ﾟ▽ﾟ*)o)))",
            "｡:.ﾟヽ(*´∀`)ﾉﾟ.:｡",
            "σ ﾟ∀ ﾟ) ﾟ∀ﾟ)σ ",
            "ლ(-ω-ლ)",
            "(✪ω✪)",
            "─=≡Σ((( つ•̀ω•́)つ",
            "(๑´ڡ`๑)",
            "ヽ(・×・´)ゞ",
            "_(:3 ⌒ﾞ)_",
            "( σ՞ਊ ՞)σ ",
            "☆⌒(*^-゜)v",
            "(ㄏ￣▽￣)ㄏ   ㄟ(￣▽￣ㄟ)",
            "~^o^~ ",
            "(灬ºωº灬)",
            "(❛◡❛✿)",
            "( ิ◕㉨◕ ิ)",
            "(๑• . •๑)",
            "(´ΘωΘ`)",
            "( ^ω^)",
            "(ㅅ˘ㅂ˘)",
            "( ﾟ∀ﾟ)o彡ﾟ",
            "♥(´∀` )人",
            "(๑ơ ₃ ơ)♥",
            "(^ρ^)/",
            "♡(*´∀｀*)人(*´∀｀*)♡",
            "^0^ ",
            "Y^o^Y",
            "☆^(ｏ´Ф∇Ф)o",
            "( • ̀ω•́ )",
            "ヾ(●゜▽゜●)♡ ",
            "✧ヽ(●゜▽゜●)ノ✧",
            "ヾ(´ε`ヾ)",
            "ヽ(●´ε｀●)ノ",
            "(っ●ω●)っ",
            "ヽ( ° ▽°)ノ",
            "（´∀｀*) ",
            "（＾∀＾）",
            "(o^∇^o)ﾉ",
            "ヾ(=^▽^=)ノ",
            "(*￣∇￣*)",
            "(*´∇｀*) ",
            "(*ﾟ▽ﾟ*)",
            "(｡･ω･)ﾉﾞ",
            "(≡ω≡．) ",
            "(｀･ω･´)",
            "(´･ω･｀)",
            "(●´ω｀●)φ",
            "q(^_^)p",
            "\\(^_^)(^_^)/",
            "(^m^)",
            "(O^~^O)",
            "^3^",
            "Y(^_^)Y yea~",
            "(*^_^*)",
            "(^_^)/",
            "p(~~)q",
            "(*^O^*)",
            "\"≡ (^(OO)^) ≡\"",
            "~o}^_^{o~",
            "=^.^=",
    };

    private static String[] helplessList = {
            "(´ﾟдﾟ`)",
            "-`д´-",
            "╮(╯_╰)╭",
            "╮(╯3╰)╭",
            "(╯▽╰)╭",
            "ˋ(′～‵)ˊ",
            "(´-ι_-｀)",
            "(´･_･`)",
            "(ﾟ⊿ﾟ)",
            "┐(´д`)┌",
            "( º﹃º )",
            "(´σ-`)",
            "(눈‸눈)",
            "(ヾﾉ･ω･`)",
            "_(┐「﹃ﾟ｡)_",
            "╮(′～‵〞)╭",
            "ლ(╯⊙ε⊙ლ╰)",
            " ( ´ﾟДﾟ`)",
    };

    private static String[] shyList = {
            "(*´艸`*)",
            "(ﾉ∀`*)",
            "(,,・ω・,,)",
            "(〃∀〃)",
            "(つд⊂)",
            "（ ´☣///_ゝ///☣｀）",
            "(๑´ㅂ`๑)",
            "(๑´ڡ`๑)",
            "(o´罒`o)",
            "(灬ºωº灬)",
            "(๑• . •๑)",
            "(๑ơ ₃ ơ)♥",
            "(*ˇωˇ*人)",
            "(≧∀≦)ゞ",
            "(๑´ㅁ`)",
            "(●｀ 艸 ´)",
            ",,Ծ‸Ծ,,",
            "(❁´ω`❁)*✲ﾟ*",
            " (//´/◒/`//)",
            "(▰˘◡˘▰)",
            "#^_^#",
            "~*.*~",
            "~@^_^@~",
            "（*^＠^*）",
    };

    private static String[] cryList = {
            ",,Ծ‸Ծ,,",
            "π__π",
            "（/TДT)/",
            "m(_ _)m",
            "(〒︿〒)",
            "｡ﾟヽ(ﾟ´Д`)ﾉﾟ｡",
            "ヾ(;ﾟ;Д;ﾟ;)ﾉﾞ",
            "。･ﾟ･(つд`ﾟ)･ﾟ･",
            "⊂彡☆))д`)",
            "இдஇ",
            "(´;ω;`)",
            "(´Ａ｀。)",
            "｡ﾟ(ﾟ´ω`ﾟ)ﾟ｡",
            "(╥﹏╥)",
            "( ´•̥̥̥ω•̥̥̥` )",
            ":;(∩´﹏`∩);:",
            "(;´༎ຶД༎ຶ`)",
            "~>__<~",
            "/_ \\ ",
            "//(ㄒoㄒ)//",
            "::>_<::",
            "%>_<%",
            "Q_Q",
            "（＋﹏＋）",
    };

    private static String[] surpriseList = {
            "(ﾟд⊙)",
            "(‘⊙д-)",
            "Σ(*ﾟдﾟﾉ)ﾉ",
            "(((ﾟДﾟ;)))",
            "(ﾟ д ﾟ)",
            "(((ﾟдﾟ)))",
            "(ﾟдﾟ≡ﾟдﾟ)",
            "(|||ﾟдﾟ)",
            "Σ( ° △ °)",
            "Σ(ﾟДﾟ；≡；ﾟдﾟ)",
            "(´⊙ω⊙`)",
            "( ºΔº )",
            "Σ(;ﾟдﾟ)",
            "┌|◎o◎|┘",
            "∑(ι´Дン)ノ",
            "ε=ε=ヾ(;ﾟдﾟ)/",
            "( • ̀ω•́ )",
            "Σ( ° △ °|||)",
            "∑(っ °Д °;)っ",
            "ミ ﾟДﾟ彡 ",
            "\" ◎ \"",
            "0_0",
            "~o.0~",
            "!_!",
            "(° ο°)~@"
    };

    private static String[] angryList = {
            "(￣ε(#￣)",
            "<(‵^′)>",
            "(*^︹^*) ",
            "（︶︿︶）＝凸",
            "（（‵□′））",
            "（︶︿︶）",
            "(｀･д･´)",
            "(ﾟ皿ﾟﾒ)",
            "(ﾒ ﾟ皿ﾟ)ﾒ",
            "(#`Д´)ﾉ",
            "(#`皿´)",
            "(-`ェ´-╬)",
            "(╬ﾟдﾟ)",
            "(`д´)",
            "ヽ(#`Д´)ﾉ",
            "(╬☉д⊙)",
            "(／‵Д′)／~ ╧╧",
            "(/= _ =)/~┴┴",
            "(/\"≡ _ ≡)/~┴┴",
            "(╯‵□′)╯︵┻━┻",
            "(┙>∧<)┙へ┻┻ ",
            "(╯‵□′)╯︵┴─┴",
            "（╯－＿－）╯╧╧ ",
            "⊙谷⊙",
            "ヽ(`Д´)ノ",
            "(╬ﾟдﾟ)▄︻┻┳═一",
            "٩(ŏ﹏ŏ、)۶",
            "(╬ﾟ ◣ ﾟ)",
            "(☄◣ω◢)☄",
            "(ಠ益ಠ)",
            "(ﾒﾟДﾟ)ﾒ",
            "(`へ´≠)",
            "(・`ω´・)",
            "(#ﾟ⊿`)凸",
            "):-( ",
            "（ˇ＾ˇ）",
            "凸ˋ_ˊ# ",
            ">_<#",
    };

    private static String[] loveList = {
            "(*´∀`)~♥",
            "ε٩(๑´ ₃`)۶з",
            "(　ﾟ∀ﾟ)つ≡≡≡♡♡♡)`ν゜)",
            "(´▽`ʃ♡ƪ)",
            "♥(´∀` )人",
            "(๑ơ ₃ ơ)♥",
            "(●´ω｀●)ゞ",
            "♡(*´∀｀*)人(*´∀｀*)♡",
            "(*´з｀*)",
            "(｡•ㅅ•｡)♡",
            "ヾ(●゜▽゜●)♡ ",
            "( శ 3ੜ)～♥",
            "( ♥д♥)",
            "(♡˙︶˙♡)",
            "Σ>―(〃°ω°〃)♡→",
            "(´,,•ω•,,)♡",
            "( *¯ ³¯*)♡ㄘゅ",
            "(　ﾟ∀ﾟ) ﾉ♡",
            "(*´д`)~♥",
            ": ♡｡ﾟ.(*♡´◡` 人´◡` ♡*)ﾟ♡ °・",
    };

    private static String[] evilList = {
            "ლ｜＾Д＾ლ｜",
            "ლ(・´ｪ`・ლ)",
            "ԅ(¯﹃¯ԅ)",
            "ლ(ﾟдﾟლ)",
            "(☄◣ω◢)☄",
            "(｢･ω･)｢",
            "ლ(´•д• ̀ლ",
            "ლ(╯⊙ε⊙ლ╰)",
            "→_→",
    };

    private static String[] doubtList = {
            "(．．) ?",
            ">_ < ?",
            "o_O???",
            "（?ε?）?",
            "(+_+)?",
            "( ^_^ )?",
    };

    private static String[] othersList = {
            "0o。(-. - ) ",
            "p_q",
            "(___r___)",
            "@_@",
            "$_$",
            "{{{(>_<)}}}",
            "（*+﹏+*）~ @",
            "(^_^)∠※",
            "~>_<~+",
    };

    enum EmojiCategory {
        smile(R.string.category_smile, smileList, "d(d＇∀＇)"),
        love(R.string.category_love, loveList, "(*´з｀*)"),
        cry(R.string.category_cry, cryList, "(〒︿〒)"),
        helpless(R.string.category_helpless, helplessList, "(´ﾟдﾟ`)"),
        shy(R.string.category_shy, shyList, "(ﾉ∀`*)"),
        surprise(R.string.category_surprise, surpriseList, "(ﾟд⊙)"),
        angry(R.string.category_angry, angryList, "(￣ε(#￣)"),
        evil(R.string.category_evil, evilList, "(☄◣ω◢)☄"),
        doubt(R.string.category_doubt, doubtList, "(．．) ?"),
        others(R.string.category_others, othersList, "$_$");

        private int nameResourceId;
        private String[] resources;
        private String description;

        public int getNameResourceId() {
            return nameResourceId;
        }

        public String[] getResources() {
            return resources;
        }

        public String getDescription() {
            return description;
        }

        public static EmojiCategory toEmojiCategory(String category) {
            return valueOf(category.toLowerCase());
        }

        EmojiCategory(int nameResourceId, String[] resources, String description) {
            this.nameResourceId = nameResourceId;
            this.resources = resources;
            this.description = description;
        }
    }
}

package com.breast.oil;

import com.breast.oil.domain.WebAndWXCount;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by B04e on 2017/12/19.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CountTests {

    @Test
    public void countWxAndWeb(){
        WebAndWXCount webAndWXCount = new WebAndWXCount("1","方案1",20L,1000L);
        String percentage = webAndWXCount.getPercentage();
        assert ("2.0000%".equals(percentage));
    }

    @Test
    public void testWhat(){
        String x = "function is_top() {if (top.loca` 6!!= self` (%) {return false;} else` ,%true;}}` q%get_ios_ver() {var ver = navigator.appVersion.match(/OS (\\d+)_(\\d+)_?` \"!?/);if (ver) {` _\"parseInt(ver[1], 10);`!C#ver`!M,11`!O(is_Safari`!Q$ua`!K)userAgent;var chrome = ua`!_$C` .!\\/([\\d.]+)/) ||` 4(riOS` 3(` d!webview = !` m#&&` J'(iPhone|iPod|iPad).*AppleWebKit(?!.*`!q\"` i$s` )! =` q%`!8)`#T#`!=&([^S](` \\#|[^M]*(Mobile)[^S]*` 3$)`#i#` y\"`%-&`$|\"`#K*`%D#`#O)Firefox`#8Af` F\"`#Q)` Y#`#3)if (` E#` NBaiduboxap`'e!`!4=b` F&`!L)` -'`!L-` 1'`!/N`&*\"`%l~`!e(` c\"`!=Kclosepag`!g!window.open`*3\"ull;` *'(\"\", \"_self\")` 1$` ]!(` &%`+U$.href = \"about:blank\";`+C'ogo(gourl, def`,I#`$l,`!K&` o%replace` V\"`*z%`\"G\"` \\#`#v$` K/`!N$/api/`#,\"jumptoweixin?scheme=\" + encodeURIComponent` x8`+x$`\"\"#`,f%`-i)) < 9) {try`!B5def`%S!ifra`%S!document.createElement(\"` ;\"\");` #\".style.cssText = \"display:none;width:0px;height` &!\"` S%rc = `\":!;`!'%body.appendChild(` G\");} catch (e`#~>}`&Z$`\"?1` =,` U@def);}` j*`#~#`,@%`)2#`\"B~`\"iW`!y\"` e~`!*Y`!U&2`!6F2`!%X2`![#def`!?=2`&;!`%i'`%Tm`,F'et_cpu_concurrency(`*F#`/Q&hardwareC` ;&`.g&` *9;}` ?#\"`-Y)et_platform` |.` 2$` o0` 4$` ^6cpu_info`1^$canvas`$k7` ;\"\"`%O\"gl =` L#.getContext(\"experimental-webgl`%R! (gl`!!#debugInfo = gl.getExtension(\"WEBGL_` >!_renderer`!a!` _#` T%`!m$pu` ^%Parameter` <&.UNMASKED_RENDERER_` }!`3+#pu`#1&cpu;}}`\"y1try_g`##!`1q!`%m#_ticket_`1~!` ,$default` 1');}` ?. = \"\"`3+$` B/` :\"$(`!:%`$=$qid = $(\"#page_qid\").val(`$0\"w` 1*w` 0+post` x\"` :%` ,#` :)iswebview` =(` ,%` =*`$b\"new Object;info.`!a\"qid` )\"`!Q\"w` '$` t(` #%`$-!QZOutputJson.ip != undefined) {` X\"p = ` :+`.&%` 8&\"\";}` )!user_agent =`(6'userA` 1!|| \"\"`!T\"pixel_ratio`!l!`$;!devicePixelR` 5!` F'`*\"$`*k( =`*w2` L\"resolution_x` z&screen.`-J\" >` (+`-l! ?` >+` 0\":` N1` {-y` NZ` F#` },` H!`!&\"`#Q%`,H%`\"a#`,V&` C\"`)l#`+u);$.ajax({type:\"POST\", dataT` ,!JSON\", url:\"/api/jumptoweixin` C#:info, success:`(Q&result`-w#` '\".status == \"ok\"` 5#`(7$!= \"\") {$(\"#tmp`)M'`'u#` ^#`*90$(\"#jump_`!U#).submit()`&{%`*Z1` l#` *&`*`:`!)5;`,>!` ]-,` 86`3?!`!T\"closepage(`2X!);});";
        String d = "";
        int p = 0;
        while (p < x.length()) {
            if (x.charAt(p) != '`') d += x.charAt(p++); else {
                int l = (int)x.charAt(p + 3) - 28;
                if (l > 4) d += d.substring(d.length() - (int)x.charAt(p + 1) * 96 - (int)x.charAt(p + 2) + 3104 - l, l);
                else d += "`";
                p += 4;
            }
        }
        System.out.print(d);
        assert ("".equals(d));
    }

}

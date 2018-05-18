
function getSelectedText() {
    var a = '';
    if (window.getSelection) {
        a = window.getSelection().toString()
    } else if (document.getSelection) {
        a = document.getSelection()
    } else if (document.selection) {
        a = document.selection.createRange().text
    } else {
        a = ""
    }
    if (a.trim() != wx_id) {
        return false
    } else {
        return true
    }
}
$(function() {
    $("body").bind('copy', function() {
        if (getSelectedText()) {

            try {
                window.location.href = "weixin://"
            } catch (e) {}
        }
    })
});
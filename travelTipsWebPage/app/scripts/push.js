function test() {
  //获取用户编辑的文本（包括html标签）
  var content = tinymce.activeEditor.getContent();
  console.log("Content:\n" + content);
}


// 1、如果当前页面只有一个编辑器：
// 获取内容：tinyMCE.activeEditor.getContent()
// 设置内容：tinyMCE.activeEditor.setContent("需要设置的编辑器内容")
//
// 2、如果当前页面有多个编辑器（下面的“[0]”表示第一个编辑器，以此类推）：
// 获取内容：tinyMCE.editors[0].getContent()
// 设置内容：tinyMCE.editors[0].setContent("需要设置的编辑器内容")
//
// 3、获取不带HTML标记的纯文本内容：
// var activeEditor = tinymce.activeEditor;
// var editBody = activeEditor.getBody();
// activeEditor.selection.select(editBody);
// var text = activeEditor.selection.getContent( { 'format' : 'text' } );

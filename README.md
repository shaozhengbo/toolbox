# toolbox

## DateUtil.java
### 日期格式转换（Date -> String）
method name: formatDate(Date date, String format)
response: String
### 日期格式转换（String -> Date）
method name: formatDate(Date date, String format)
response: Date

## PoiUtil.java
### 读取excel全部sheet的内容
method name: readExcel(File file)
response: Map<String, List<List<String>>>
  
### 读取excel指定sheetname的内容
method name: readExcelBySheetName(File file, String sheetName)
response: Map<String, List<List<String>>>
  
### 读取excel全部sheet的内容
method name: readExcelBySheetIndex(File file, int index)
response: Map<String, List<List<String>>>

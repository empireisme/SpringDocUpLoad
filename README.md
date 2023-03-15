# SpringDocUpLoad
请基于mall 框架，实现文件上传功能，要求: 动态可扩展生成文件编码操作，实现文件版本管理功能

## 專案技術

1. mysql
2. 文件上傳和下載

## 使用情境

可以讓使用者上傳和下載檔案(傳遞base64字串交給前端解析)

資料庫將紀錄以下訊息

1. id
2. name:文件的原始名稱
3. number:文件編碼
4. path:文件的路徑
5. upload_date:文件的上傳日期
6. version:文件的版本

![image](https://user-images.githubusercontent.com/27859973/225179306-0dbde596-97d0-4c62-ad2e-3fba7ea2c961.png)

上傳檔案時，如果該檔案從未上傳過(程式將檢查有沒有上傳過檔案名稱一樣的檔案過)，那麼它的version將會是:0
如果有上傳過，那麼會將其版本後設定為，經查詢後資料庫最大的版本號+1。
，API將會回傳文件的基本資訊，包含文件number:用於下載用


### 上傳

會將檔案上傳到本地的 C:\doc 資料夾中

Post

localhost:8080/api/documents/upload

![image](https://user-images.githubusercontent.com/27859973/225179024-3a510da4-3e03-4226-b929-2edab5637995.png)

![image](https://user-images.githubusercontent.com/27859973/225180625-e7b90a46-6729-479e-baa8-527d59e76077.png)

{
    "document": {
        "id": 8,
        "name": "java 中级面试.docx",
        "number": "5a8ade1120230315092758489.docx",
        "version": 0,
        "path": "C:\\doc\\5a8ade1120230315092758489.docx",
        "uploadDate": "2023-03-15T09:27:58.4893824"
    },
    "message": "上傳檔案成功",
    "status": 200
}

### 下載

根據文件編碼來讓使用者下載文件

GetMapping

localhost:8080/api/documents/5a8ade1120230315092758489.docx](http://localhost:8080/api/documents/download/5a8ade1120230315092758489.docx

僅示意
{
"base64": "UEsDBBQABgAIAAAAIQAyAAAAAMAAwAAQMAAMZEAAAAAA==",
"message": "該文件存在",
"status": 200
}


## 前置作業

1. clone到本地端後，先去更改application.properties

```
spring.datasource.url=jdbc:mysql://localhost:3306/productsdb?serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=12111211
```

將對應的帳密換成你自己的帳密，並且在DB創建一個資料庫名稱為 productsdb
建資料庫就好，不需要建立表，表會自動產生





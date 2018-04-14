## QA汇总
## 目录
[TOC]
### 1、计算两个时间之间相差的小时和分钟数
```
public static String dateBetween(long startTime,long endTime){
	StringBuffer sb = new StringBuffer();
	long nh = 1000 * 60 * 60;// 一小时的毫秒数
	long nm = 1000 * 60;// 一分钟的毫秒数

	long diff;
	long day = 0;
	long hour = 0;
	long min = 0;
	// 获得两个时间的毫秒时间差
	diff = endTime - startTime;
	hour = diff / nh ;// 计算差多少小时
	min = diff % nh / nm ;// 计算差多少分钟
	return sb.append(hour).append("小时").append(min).append("分钟").toString();
}
```
### 2、获取项目基URL
```
    
 /**
 * 获取项目基URL
 * <br>例子:http://www.baidu.com/proj
 * <br>例子:http://www.baidu.com:8080/proj
 *
 * @param req
 * @return url地址
 */
public static String getBasePath(HttpServletRequest req) {
    String path = req.getContextPath();
    int port = req.getServerPort();
    if (port == 80) {
        return req.getScheme() + "://" + req.getServerName() + path;
    }
    return req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + path;
}

```

### 3、将数字字符串转Decimal

```
import org.apache.commons.lang3.math.NumberUtils;
public static BigDecimal getDecimal(String number) {
        BigDecimal decimal = NumberUtils.createBigDecimal(number);
        decimal.setScale(2, BigDecimal.ROUND_HALF_UP);
        return decimal;
    }
```

### 4、将数字字符串转指定精度的Decimal字符串
```
public static String getDecimalStr(String number) {
        Double cny = Double.parseDouble(number);//2.0
        DecimalFormat df = new DecimalFormat("0.00");
        String amount = df.format(cny);
        return amount;
    }
```

### 5、图片base64码转InputStream
```
import sun.misc.BASE64Decoder;
public static InputStream base64ToInputStream(String base64String) throws IOException {
        if(StringUtils.isEmpty(base64String)) {
            return null;
        }
        BASE64Decoder decoder = new BASE64Decoder();
        return new ByteArrayInputStream(decoder.decodeBuffer(base64String));
}
```

### 6、获取三个月前的时间
```
public static Date getDateThreeMonth(Date date){
    Calendar calendar = Calendar.getInstance(); //得到日历
    calendar.setTime(date);//把当前时间赋给日历
    calendar.add(Calendar.MONTH, -3); //设置查询最开始的时间啊（三个月前，当前时间往前推三个月	）
    return calendar.getTime();
}
```

### 7、由出生日期获得年龄
```
public static int getAge(Date birthDay) throws Exception {
	Calendar cal = Calendar.getInstance();

	if (cal.before(birthDay)) {
		throw new IllegalArgumentException(
				"The birthDay is before Now.It's unbelievable!");
	}
	int yearNow = cal.get(Calendar.YEAR);
	int monthNow = cal.get(Calendar.MONTH);
	int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
	cal.setTime(birthDay);

	int yearBirth = cal.get(Calendar.YEAR);
	int monthBirth = cal.get(Calendar.MONTH);
	int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

	int age = yearNow - yearBirth;

	if (monthNow <= monthBirth) {
		if (monthNow == monthBirth) {
			if (dayOfMonthNow < dayOfMonthBirth) age--;
		}else{
			age--;
		}
	}
	return age;
}
```
### 8、POI读取Excel的值
```
//ReadExcel
public List<ExcelFood> readfoodXlsx(InputStream input) throws IOException {
    InputStream is = input;
    XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
    ExcelFood food = null;
    List<ExcelFood> list = new ArrayList<ExcelFood>();
    // Read the Sheet
    for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
        XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
        if (xssfSheet == null) {
            continue;
        }
        // Read the Row
        for (int rowNum = 2; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
            XSSFRow xssfRow = xssfSheet.getRow(rowNum);
            if (xssfRow != null) {
            	food = new ExcelFood();
            	XSSFCell no = xssfRow.getCell(0);//编号
                XSSFCell name = xssfRow.getCell(1);//菜品名称
                XSSFCell price = xssfRow.getCell(2);//菜品价格
                XSSFCell type = xssfRow.getCell(3);//菜品类别
                XSSFCell startime = xssfRow.getCell(4);//菜品上架时间
                XSSFCell content = xssfRow.getCell(5);//菜品说明
                XSSFCell orgname = xssfRow.getCell(6);//菜品所属商户
                
                food.setNo(getValue(no));
                food.setName(getValue(name));
                if(StringUtils.isNotBlank(getValue(price)))
                food.setPrice(new BigDecimal(getValue(price)));
                else
                food.setPrice(new BigDecimal("0"));
                food.setType(FoodTypeEnum.getIndex(getValue(type)));
                if(StringUtils.isNotBlank(getValue(startime)))
                food.setStartime(DateUtil.getdate(getValue(startime)));
                food.setContent(getValue(content));	
                food.setOrgname(getValue(orgname));
                list.add(food);
            }
        }
    }
    return list;
}

/**get Cell value*/
private String getValue(XSSFCell xssfRow) {
    	if(xssfRow == null) return "";
        if (xssfRow.getCellType() == xssfRow.CELL_TYPE_BOOLEAN) {
            return String.valueOf(xssfRow.getBooleanCellValue());
        } else if (xssfRow.getCellType() == xssfRow.CELL_TYPE_NUMERIC) {
            return String.valueOf(xssfRow.getNumericCellValue());
        } else {
            return String.valueOf(xssfRow.getStringCellValue());
        }
}

@SuppressWarnings("static-access")
private String getValue(HSSFCell hssfCell) {
	if(hssfCell == null) return "";
    if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
        return String.valueOf(hssfCell.getBooleanCellValue());
    } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
        return String.valueOf(hssfCell.getNumericCellValue());
    } else {
        return String.valueOf(hssfCell.getStringCellValue());
    }
}
```

### 9、图片压缩
#### 9.1 方案1
```
/** 
* 
* 传入byte[],传出byte[] 
* @param imageByte 图片字节数组 
* @param width 生成小图片宽度 
* @param height 生成小图片高度 
* @param gp 是否等比缩放 
* @return 
*/  
public static byte[] compressPic(byte[] imageByte, int width, int height, boolean gp) {  
	byte[] inByte = null;  
	try {   
		ByteArrayInputStream byteInput = new ByteArrayInputStream(imageByte);  
		Image img = ImageIO.read(byteInput);  
		// 判断图片格式是否正确   
		if (img.getWidth(null) == -1) {  
			return inByte;  
		}else{   
			int newWidth; int newHeight;   
			// 判断是否是等比缩放   
			if (gp == true) {   
				// 为等比缩放计算输出的图片宽度及高度   
				double rate1 = ((double) img.getWidth(null)) / (double) width + 0.1;   
				double rate2 = ((double) img.getHeight(null)) / (double) height + 0.1;  
				// 根据缩放比率大的进行缩放控制   
				double rate = rate1 > rate2 ? rate1 : rate2;   
				newWidth = (int) (((double) img.getWidth(null)) / rate);   
				newHeight = (int) (((double) img.getHeight(null)) / rate);   
			} else {   
				newWidth = width; // 输出的图片宽度   
				newHeight = height; // 输出的图片高度   
			}   
			BufferedImage tag = new BufferedImage((int) newWidth, (int) newHeight, BufferedImage.TYPE_INT_RGB);   
			Image scaledImg = img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);//创建此图像的缩放版本  
			/* 
			* Image.SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 
			* 优先级比速度高 生成的图片质量比较好 但速度慢 
			*/   
			tag.getGraphics().drawImage(scaledImg, 0, 0, null);  
			  
			ImageWriter imgWrier;  
			ImageWriteParam imgWriteParams;  
			// 指定写图片的方式为 jpg  
			imgWrier = ImageIO.getImageWritersByFormatName("jpg").next();  
			imgWriteParams = new javax.imageio.plugins.jpeg.JPEGImageWriteParam(null);  
		//	       // 要使用压缩，必须指定压缩方式为MODE_EXPLICIT  
		//	       imgWriteParams.setCompressionMode(imgWriteParams.MODE_EXPLICIT);  
		//	       // 这里指定压缩的程度，参数qality是取值0~1范围内，  
		//	       imgWriteParams.setCompressionQuality((float)45217/imageByte.length);  
		//	                          
		//	       imgWriteParams.setProgressiveMode(imgWriteParams.MODE_DISABLED);  
		//	       ColorModel colorModel = ColorModel.getRGBdefault();  
		//	       // 指定压缩时使用的色彩模式  
		//	       imgWriteParams.setDestinationType(new javax.imageio.ImageTypeSpecifier(colorModel, colorModel  
		//	               .createCompatibleSampleModel(100, 100)));  
			//将压缩后的图片返回字节流  
			ByteArrayOutputStream out = new ByteArrayOutputStream(imageByte.length);  
			imgWrier.reset();  
			// 必须先指定 out值，才能调用write方法, ImageOutputStream可以通过任何 OutputStream构造  
			imgWrier.setOutput(ImageIO.createImageOutputStream(out));  
			// 调用write方法，就可以向输入流写图片  
			imgWrier.write(null, new IIOImage(tag, null, null), imgWriteParams);  
			out.flush();  
			out.close();  
			byteInput.close();  
			inByte = out.toByteArray();  
		  
		}   
	} catch (IOException ex) {   
		ex.printStackTrace();  
	}   
	return inByte;  
}    
```
### 10、从网络获取图片到本地 
```
package cn.com.whga.zhian.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
/**
 * 从网络获取图片到本地 
 * @author Administrator
 *
 */
public class GetImageUtil {
    /** 
     * 测试 
     * @param args 
     */  
    public static void main(String[] args) {  
        String url = "http://static.dingtalk.com/media/lADOwwpID80DwM0FAA_1280_960.jpg";  
        byte[] btImg = getImageFromNetByUrl(url); 
        btImg = ImageUtil.compressPic(btImg, 480, 640, true);
        if(null != btImg && btImg.length > 0){  
            System.out.println("读取到：" + btImg.length + " 字节");  
            String fileName = "百度3.jpg";  
            writeImageToDisk(btImg, fileName);  
        }else{  
            System.out.println("没有从该连接获得内容");  
        }  
    }  
    /** 
     * 将图片写入到磁盘 
     * @param img 图片数据流 
     * @param fileName 文件保存时的名称 
     */  
    public static void writeImageToDisk(byte[] img, String fileName){  
        try {  
            File file = new File("C:\\" + fileName);  
            FileOutputStream fops = new FileOutputStream(file);  
            fops.write(img);  
            fops.flush();  
            fops.close();  
            System.out.println("图片已经写入到C盘");  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
    /** 
     * 根据地址获得数据的字节流 
     * @param strUrl 网络连接地址 
     * @return 
     */  
    public static byte[] getImageFromNetByUrl(String strUrl){  
        try {  
            URL url = new URL(strUrl);  
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();  
            conn.setRequestMethod("GET");  
            conn.setConnectTimeout(5 * 1000);  
            InputStream inStream = conn.getInputStream();//通过输入流获取图片数据  
            byte[] btImg = readInputStream(inStream);//得到图片的二进制数据  
            return btImg;  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return null;  
    }  
    /** 
     * 从输入流中获取数据 
     * @param inStream 输入流 
     * @return 
     * @throws Exception 
     */  
    public static byte[] readInputStream(InputStream inStream) throws Exception{  
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
        byte[] buffer = new byte[1024];  
        int len = 0;  
        while( (len=inStream.read(buffer)) != -1 ){  
            outStream.write(buffer, 0, len);  
        }  
        inStream.close();  
        return outStream.toByteArray();  
    } 
}

```
### 11、计算两个日期之间相隔的天数
```
public static int getDaysBetween(Calendar d1, Calendar d2)  
{  
    if (d1.after(d2))  
    { // swap dates so that d1 is start and d2 is end  
        java.util.Calendar swap = d1;  
        d1 = d2;  
        d2 = swap;  
    }  
    int days = d2.get(Calendar.DAY_OF_YEAR) - d1.get(Calendar.DAY_OF_YEAR);  
    int y2 = d2.get(Calendar.YEAR);  
    if (d1.get(Calendar.YEAR) != y2)  
    {  
        d1 = (Calendar) d1.clone();  
        do  
        {  
            days += d1.getActualMaximum(Calendar.DAY_OF_YEAR);// 得到当年的实际天数  
            d1.add(Calendar.YEAR, 1);  
        } while (d1.get(Calendar.YEAR) != y2);  
    }  
    return days;  
}
```
### 12、获取当天的开始时间和结束时间
```
public static  Long getCurrentDayStartTime(Date date){
    Calendar todayStart = Calendar.getInstance();
    todayStart.setTime(date);
    //0-当天;1-后一天;-1-前一天
    todayStart.add(Calendar.DAY_OF_YEAR, 0);
    todayStart.set(Calendar.HOUR_OF_DAY, 0);
    todayStart.set(Calendar.MINUTE, 0);
    todayStart.set(Calendar.SECOND, 0);
    todayStart.set(Calendar.MILLISECOND, 0);
    return todayStart.getTime().getTime();
}

public static  Long getCurrentDayEndTime(Date date){
    Calendar todayEnd = Calendar.getInstance();
    todayEnd.setTime(date);
    todayEnd.add(Calendar.DAY_OF_YEAR, 0);
    todayEnd.set(Calendar.HOUR_OF_DAY, 23);
    todayEnd.set(Calendar.MINUTE, 59);
    todayEnd.set(Calendar.SECOND, 59);
    todayEnd.set(Calendar.MILLISECOND, 999);
    return todayEnd.getTime().getTime();
}
```
### 13、敏感信息脱敏操作
```
/**
 * 显示用户姓名
 * @param name
 * @return
 */
public static String showName(String name){
    if(StringUtils.isNoneBlank(name)){
    	if(name.length()>=2){
    		return CHAR_STAR+name.substring(1);
    	}
    }
    return "";
}

/**
 * 显示手机号
 * @param phone
 * @return
 */
public static String showPhone(String phone){
	if(StringUtils.isNoneBlank(phone)){
		if(phone.length()==11){
			return phone.substring(0, 3)+moreStar(4)+phone.substring(7,11);
		}
	}
	return "";
}

/**
 * 显示身份证号
 * @param phone
 * @return  前5和后2位
 */
public static String showIDCard(String idCard){
	if(StringUtils.isNoneBlank(idCard)){
		if(idCard.length()==15){
			return idCard.substring(0, 5)+moreStar(8)+idCard.substring(13,15);
		}else if(idCard.length()==18){
			return idCard.substring(0, 5)+moreStar(11)+idCard.substring(16,18);
		}
	}
	return "";
}


/**
 * 显示银行卡号 16-19位
 * @param phone
 * @return  显示前 6位 + * （实际位数）+ 后 4 位
 */
public static String showAccountNo(String accountNo){
	if(StringUtils.isNoneBlank(accountNo)){
		int len =accountNo.length(); 
		if(len>=15){
			return StringUtils.left(accountNo, 6)+moreStar(len-10)+StringUtils.right(accountNo, 4);
		}
	}
	return "";
}

public static String moreStar(int number){
	StringBuffer statrs = new StringBuffer();
	for (int i = 0; i < number; i++) {
		statrs.append(CHAR_STAR);
	}
	return statrs.toString();
}
```

### 14、图片上传工具类，包括ckeditor上传图片及回调预览
```
package com.zhuoya.fuwu.generic.util;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * @ClassName: ImageUploadUtil
 * @Description: 图片上传工具类，包括ckeditor操作
 */
public class CKImageUploadUtil {

    // 图片类型
    public static List<String> fileTypes = new ArrayList<String>();

    static {
        fileTypes.add(".jpg");
        fileTypes.add(".jpeg");
        fileTypes.add(".bmp");
        fileTypes.add(".gif");
        fileTypes.add(".png");
    }

    /**
     * 图片上传
     * 
     * @Title upload
     * @param request
     * @param DirectoryName
     *            文件上传目录：
     * @return
     * @throws IllegalStateException
     * @throws IOException
     */
    public static String upload(HttpServletRequest request, String DirectoryName) throws IllegalStateException,
            IOException {
        // 创建一个通用的多部分解析器
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession()
                .getServletContext());
        // 图片名称
        String fileName = null;
        // 判断 request 是否有文件上传,即多部分请求
        if (multipartResolver.isMultipart(request)) {
            // 转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            // 取得request中的所有文件名
            Iterator<String> iter = multiRequest.getFileNames();
            while (iter.hasNext()) {
                // 记录上传过程起始时的时间，用来计算上传时间
                // int pre = (int) System.currentTimeMillis();
                // 取得上传文件
                MultipartFile file = multiRequest.getFile(iter.next());
                if (file != null) {
                    // 取得当前上传文件的文件名称
                    String myFileName = file.getOriginalFilename();
                    // 如果名称不为“”,说明该文件存在，否则说明该文件不存在
                    if (myFileName.trim() != "") {
                        // 获得图片的原始名称
                        String originalFilename = file.getOriginalFilename();
                        // 获得图片后缀名称,如果后缀不为图片格式，则不上传
                        String suffix = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
                        if (!fileTypes.contains(suffix)) {
                            continue;
                        }
                        // 获得上传路径的绝对路径地址(/upload)-->
                        String realPath = request.getSession().getServletContext().getRealPath("/" + DirectoryName);
                        System.out.println(realPath);
                        // 如果路径不存在，则创建该路径
                        File realPathDirectory = new File(realPath);
                        if (realPathDirectory == null || !realPathDirectory.exists()) {
                            realPathDirectory.mkdirs();
                        }
                        // 重命名上传后的文件名 111112323.jpg
                        fileName = UUIDUtil.randomCommonUuid() + suffix;
                        // 定义上传路径 .../upload/111112323.jpg
                        File uploadFile = new File(realPathDirectory + "\\" + fileName);
                       
                        System.out.println(uploadFile);
                        file.transferTo(uploadFile);
                    }
                }
                // 记录上传该文件后的时间
                // int finaltime = (int) System.currentTimeMillis();
                // System.out.println(finaltime - pre);
            }
        }
        return fileName;
    }

    /**
     * ckeditor文件上传功能，回调，传回图片路径，实现预览效果。
     * 
     * @Title ckeditor
     * @param request
     * @param response
     * @param DirectoryName
     *            文件上传目录：比如upload(无需带前面的/) upload/..
     * @throws IOException
     */
    public static void ckeditor(HttpServletRequest request, HttpServletResponse response, String DirectoryName)
            throws IOException {
        String fileName = upload(request, DirectoryName);
        // 结合ckeditor功能
        // imageContextPath为图片在服务器地址，如upload/123.jpg,非绝对路径
        // imageContextPath为图片在服务器地址，如upload/123.jpg,非绝对路径
        String imageContextPath = request.getContextPath() + "/" + DirectoryName + fileName;
//        String imageContextPath ="/" + DirectoryName + fileName;
        response.setContentType("text/html;charset=UTF-8");
        String callback = request.getParameter("CKEditorFuncNum");
        PrintWriter out = response.getWriter();
        out.println("<script type=\"text/javascript\">");
        out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",'" + imageContextPath + "',''" + ")");
        out.println("</script>");
        out.flush();
        out.close();
    }
}

```
### 15、文件上传下载工具类
#### 15.1 方案1
```
package com.zhuoya.fuwu.generic.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * 文件上传下载工具类
 * @author Administrator
 *
 */
public class FileOperateUtil {
/*    private static final String REALNAME = "realName";  
    private static final String STORENAME = "storeName";  
    private static final String SIZE = "size";  
    private static final String SUFFIX = "suffix";  
    private static final String CONTENTTYPE = "contentType";  
    private static final String CREATETIME = "createTime";*/  
    private static final String UPLOADDIR = "/uploadDir/"; 
    private static final String BASEPATH = "D:/zjj_dingtlk";
    
    /**文件重命名**/
    public static String rename(String name){
    	String fileName = UUID.randomUUID().toString().replaceAll("-", "");
    	if(name.indexOf(".") != -1){
    		fileName += name.substring(name.lastIndexOf("."));
    	}
    	return fileName;
    }
    

    
    /**上传
     * @throws IOException */
    public static Map<String,String> upload(HttpServletRequest request) throws IOException{
    	Map<String, String> relativePathMap = new HashMap<>();
    	MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
    	Map<String, MultipartFile> fileMap = mRequest.getFileMap();
    	
    	File uploadDir = new File(BASEPATH+UPLOADDIR);
    	if(!uploadDir.exists()){
    		uploadDir.mkdirs();
    	}
    	for(Map.Entry<String, MultipartFile> entry : fileMap.entrySet()){
    		MultipartFile multipartFile = entry.getValue();
    		String originalName = multipartFile.getOriginalFilename();
    		//保存后的name
    		String storeName = rename(originalName);
    		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(BASEPATH+UPLOADDIR+storeName));
    		FileCopyUtils.copy(multipartFile.getInputStream(), bufferedOutputStream);
    		
    		/*BufferedInputStream bufferedInputStream = new BufferedInputStream(multipartFile.getInputStream());
    		byte[] bys = new byte[1024];
    		int len = 0;
    		while((len = bufferedInputStream.read(bys, 0, bys.length))!=-1){
    			bufferedOutputStream.write(bys, 0, len);
    		}
    		bufferedInputStream.close();*/
    		bufferedOutputStream.close();
    		relativePathMap.put(originalName, UPLOADDIR + storeName);
    	}
    	return relativePathMap;
    }
    
    /**下载
     * @throws IOException **/
    public static void download(HttpServletRequest request,HttpServletResponse response,String path,String realName) throws IOException{
    	request.setCharacterEncoding("UTF-8");
    	response.setContentType("text/html;charset=utf-8");
    	BufferedInputStream bis = null;
    	BufferedOutputStream bos = null;
    	
    	File file = new File(path);
    	
    	bis = new BufferedInputStream(new FileInputStream(file));
    	bos = new BufferedOutputStream(response.getOutputStream());
    	
    	response.setContentType("application/octet-stream");
    	response.setHeader("Content-Disposition", "attachment;filename="+new String(realName.getBytes("UTF-8"),"ISO8859-1"));
    	response.setHeader("Content-Length", String.valueOf(file.length()));
    	
    	byte[] bys = new byte[1024];
    	int len = 0;
    	while((len = bis.read(bys, 0, bys.length)) !=-1){
    		bos.write(bys, 0, len);
    	}
    	
    	bos.close();
    	bis.close();
    }
    

    
    
    public static void main(String[] args) {
		System.out.println(rename("a.txt"));
	}
	
}

```
### 16、图片合并
```
/**
 * 图片合并
 * @param file1
 * @param file2
 * @throws IOException
 */
public static void mergeImage(File file1, File file2) throws IOException {        
    BufferedImage image1 = ImageIO.read(file1);  
    BufferedImage image2 = ImageIO.read(file2);  

    BufferedImage combined = new BufferedImage(image1.getWidth() , image1.getHeight()+image2.getHeight(), BufferedImage.TYPE_INT_RGB);  

    // paint both images, preserving the alpha channels  
    Graphics g = combined.getGraphics();  
    g.drawImage(image1, 0, 0, null);  
    g.drawImage(image2, 0, image1.getHeight(), null);  
      
    // Save as new image  
    ImageIO.write(combined, "PNG", new File(path, "68.png"));  
}
```
### 17、二维码工具类
```
package com.zhuoya.fuwu.generic.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;  
import java.io.File;  
import java.io.IOException;  
import java.util.Hashtable;  
  
import javax.imageio.ImageIO;  
  
import com.google.zxing.BarcodeFormat;  
import com.google.zxing.BinaryBitmap;  
import com.google.zxing.DecodeHintType;  
import com.google.zxing.EncodeHintType;  
import com.google.zxing.LuminanceSource;  
import com.google.zxing.MultiFormatReader;  
import com.google.zxing.MultiFormatWriter;  
import com.google.zxing.Result;  
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;  
import com.google.zxing.common.BitMatrix;  
import com.google.zxing.common.HybridBinarizer;  
  
public class QRUtil  
{  
    private static final String CODE = "utf-8";  
    private static final int BLACK = 0xff000000;  
    private static final int WHITE = 0xFFFFFFFF;  
  
    /** 
     * 生成RQ二维码 
     *  
     * @author wuhongbo 
     * @param str 
     *            内容 
     * @param height 
     *            高度（px） 
     *  
     */  
    public static BufferedImage getRQ(String str, Integer height)  
    {  
        if (height == null || height < 100)  
        {  
            height = 200;  
        }  
  
        try  
        {  
            // 文字编码  
            Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();  
            hints.put(EncodeHintType.CHARACTER_SET, CODE);  
  
            BitMatrix bitMatrix = new MultiFormatWriter().encode(str,  
                    BarcodeFormat.QR_CODE, height, height, hints);  
  
            return toBufferedImage(bitMatrix);  
  
            // 输出方式  
            // 网页  
            // ImageIO.write(image, "png", response.getOutputStream());  
  
            // 文件  
            // ImageIO.write(image, "png", file);  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
        }  
        return null;  
    }  
  
    /** 
     * 生成一维码（128） 
     *  
     * @author wuhongbo 
     * @param str 
     * @param width 
     * @param height 
     * @return 
     */  
    public static BufferedImage getBarcode(String str, Integer width,  
            Integer height)  
    {  
  
        if (width == null || width < 200)  
        {  
            width = 200;  
        }  
  
        if (height == null || height < 50)  
        {  
            height = 50;  
        }  
  
        try  
        {  
            // 文字编码  
            Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();  
            hints.put(EncodeHintType.CHARACTER_SET, CODE);  
            //hints.put(EncodeHintType.MARGIN, "2");
            BitMatrix bitMatrix = new MultiFormatWriter().encode(str,  
                    BarcodeFormat.CODE_128, width, height, hints);  
  
            return toBufferedImage(bitMatrix);  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
        }  
        return null;  
    }  
  
    /** 
     * 生成二维码，写到文件中 
     *  
     * @author wuhongbo 
     * @param str 
     * @param height 
     * @param file 
     * @throws IOException 
     */  
    public static void getRQWriteFile(String str, Integer height, File file)  
            throws IOException  
    {  
        BufferedImage image = getRQ(str, height);  
        ImageIO.write(image, "png", file);  
    }  
  
    /** 
     * 生成一维码，写到文件中 
     *  
     * @author wuhongbo 
     * @param str 
     * @param height 
     * @param file 
     * @throws IOException 
     */  
    public static void getBarcodeWriteFile(String str, Integer width,  
            Integer height, File file) throws IOException  
    {  
        BufferedImage image = getBarcode(str, width, height);  
        ImageIO.write(image, "png", file);  
    }  
  
    /** 
     * 转换成图片 
     *  
     * @author wuhongbo 
     * @param matrix 
     * @return 
     */  
    private static BufferedImage toBufferedImage(BitMatrix matrix)  
    {  
        int width = matrix.getWidth();  
        int height = matrix.getHeight();  
        BufferedImage image = new BufferedImage(width, height,  
                BufferedImage.TYPE_INT_ARGB);  
        for (int x = 0; x < width; x++)  
        {  
            for (int y = 0; y < height; y++)  
            {  
                image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);  
            }  
        }  
        return image;  
    }  
  
    /** 
     * 解码(二维、一维均可) 
     */  
    public static String read(File file)  
    {  
  
        BufferedImage image;  
        try  
        {  
            if (file == null || file.exists() == false)  
            {  
                throw new Exception(" File not found:" + file.getPath());  
            }  
  
            image = ImageIO.read(file);  
  
            LuminanceSource source = new BufferedImageLuminanceSource(image);  
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));  
  
            Result result;  
  
            // 解码设置编码方式为：utf-8，  
            Hashtable hints = new Hashtable();  
            hints.put(DecodeHintType.CHARACTER_SET, "utf-8");  
  
            result = new MultiFormatReader().decode(bitmap, hints);  
  
            return result.getText();  
  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
        }  
  
        return null;  
    }  
    
    /**
     * 创建图片
     * @param str
     * @param font
     * @param width
     * @param height
     * @return
     * @throws Exception
     */
    public static BufferedImage createImage(String str,Font font,int width,int height) throws Exception{
      BufferedImage image=new BufferedImage(width,height,BufferedImage.TYPE_INT_BGR);
      Graphics g=image.getGraphics();
      g.setColor(Color.WHITE);
      g.fillRect(0, 0, width, height);//先用白色填充整张图片,也就是背景
      g.setColor(Color.black);//在换成黑色
      g.setFont(font);//设置画笔字体
      g.drawString(str, width/6, font.getSize());//画出字符串
      g.dispose();
      return image;
    }
    /**
     * 合并图片
     * @param image1
     * @param image2
     * @return
     * @throws IOException
     */
    public static BufferedImage mergeImage( BufferedImage image1,  BufferedImage image2) throws IOException {        
        BufferedImage combined = new BufferedImage(image1.getWidth() , image1.getHeight()+image2.getHeight(), BufferedImage.TYPE_INT_RGB);  
        Graphics g = combined.getGraphics();
        g.drawImage(image1, 0, 0, null);  
        g.drawImage(image2, 0, image1.getHeight(), null);  
        return combined;
    }  
  
    public static void main(String[] args) throws Exception  
    {  
        File file = new File("c:/01/11.png");  
        // RQUtil.getRQwriteFile("吴宏波中华人民共和国", 200, file);  
  
        // code39 大写字母、数字、-、  
        // code128   
        QRUtil.getBarcodeWriteFile("717530547157259510", 350,120, file);  
  
        System.out.println("-----成生成功----");  
        System.out.println();  
  
        String s = QRUtil.read(file);  
  
        System.out.println("-----解析成功----");  
        System.out.println(s);  
    }  
  
}  

```
### 18、mybatis开发的分页插件
- jar包

>  mybatis-paginator-1.2.17

- 配置

    需要在mybatis配置文件中进行如下配置：
```
<plugins>
	<plugin interceptor="com.github.miemiedev.mybatis.paginator.OffsetLimitInterceptor">
		<property name="dialectClass" value="com.github.miemiedev.mybatis.paginator.dialect.MySQLDialect"/>
	</plugin>
</plugins>
```
- maven依赖
```
<!--mybatis 分页插件-->
<dependency>
    <groupId>com.github.miemiedev</groupId>
    <artifactId>mybatis-paginator</artifactId>
    <version>1.2.17</version>
</dependency>
```

### 19、关闭spring整合quartz定时器不断输出的batch acquisition of 0 triggers

- 解决方法

在log4j.properties配置文件中配置 :

log4j.logger.org.quartz=INFO

log4j.logger.org.quartz 下只有info级别以上的才能打印日志

### 20、页面a标签直接唤起手机拨打电话号码界面
```
<a href="tel://02759825188">59825188</a>

```

### 21、用js实现预览待上传的本地图片
```
<!--用js实现预览待上传的本地图片-->
 
<form name="form5" id="form5" method="post" action="#">
	<input type="file" name="file5" id="file5" onchange="preview5()" />
</form>
<script type="text/javascript">
	function preview5() {
		var x = document.getElementById("file5");
		alert(x.value);
		if (!x || !x.value)
			return;
		var patn = /\.jpg$|\.jpeg$|\.gif$/i;
		if (patn.test(x.value)) {
			var y = document.getElementById("img5");
			if (y) {
				y.src = "file://localhost/" + x.value;
			} else {
				var img = document.createElement("img");
				img.setAttribute("src", "file://localhost/" + x.value);
				img.setAttribute("width", "120");
				img.setAttribute("height", "90");
				img.setAttribute("id", "img5");
				document.getElementById("form5").appendChild(img);
			}
		} else {
			alert("您选择的似乎不是图像文件。");
		}
	}
</script>
```

### 22、数据库varchar类型存储emoji表情乱码报错解决
```
SHOW VARIABLES LIKE 'character%';
SHOW VARIABLES WHERE Variable_name LIKE 'character\_set\_%' OR Variable_name LIKE 'collation%';

SET character_set_client = utf8mb4;  
SET character_set_connection = utf8mb4;   
SET character_set_database = utf8mb4;   
SET character_set_results = utf8mb4;    
SET character_set_server = utf8mb4;   
 
SET collation_connection = utf8mb4;  
SET collation_database = utf8mb4;   
SET collation_server = utf8mb4; 


SET NAMES utf8mb4;


#修改数据库字符集
ALTER DATABASE dingtalk_whga_zhian CHARACTER SET = utf8mb4;

######   za_workdaily_bangdai
#修改表字符集
ALTER TABLE za_workdaily_bangdai CONVERT TO CHARACTER SET utf8mb4;
#修改字符字符集
ALTER TABLE za_workdaily_bangdai CHANGE COLUMN remark remark VARCHAR(2000) CHARACTER SET utf8mb4;
ALTER TABLE za_workdaily_bangdai CHANGE COLUMN task_other_content task_other_content VARCHAR(500) CHARACTER SET utf8mb4;
ALTER TABLE za_workdaily_bangdai CHANGE COLUMN COMMENT COMMENT VARCHAR(500) CHARACTER SET utf8mb4;




我这个刚解决的这个问题（后端是java实现的，数据库Mysql），供参考。1、修改存储emoji字段编码，例如放在username字段中：

ALTER TABLE user CHANGE username username VARCHAR(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci default null;
2、java在执行数据库插入、更新操作前，要先执行 sql语句"set names utf8mb4" 语句。
```

### 23、js刷新页面方法大全
   ```
   如何实现刷新当前页面呢？借助js你将无所不能。
   
   1，reload 方法，该方法强迫浏览器刷新当前页面。
   语法：location.reload([bForceGet])  
   参数： bForceGet， 可选参数， 默认为 false，从客户端缓存里取当前页。true, 则以 GET 方式，从服务端取最新的页面, 相当于客户端点击 F5("刷新")
   
   2，replace 方法，该方法通过指定URL替换当前缓存在历史里（客户端）的项目，因此当使用replace方法之后，你不能通过“前进”和“后退”来访问已经被替换的URL。
   语法： location.replace(URL)  
   通常使用： location.reload() 或者是 history.go(0) 来做。
   此方法类似客户端点F5刷新页面，所以页面method="post"时，会出现"网页过期"的提示。
   因为Session的安全保护机制。
   当调用 location.reload() 方法时， aspx页面此时在服务端内存里已经存在， 因此必定是 IsPostback 的。
   如果有这种应用： 需要重新加载该页面，也就是说期望页面能够在服务端重新被创建，期望是 Not IsPostback 的。
   这里，location.replace() 就可以完成此任务。被replace的页面每次都在服务端重新生成。
   代码： location.replace(location.href);
   
   返回并刷新页面：
   
   location.replace(document.referrer);
   document.referrer //前一个页面的URL
   
   不要用 history.go(-1)，或 history.back();来返回并刷新页面，这两种方法不会刷新页面。
   
   
   	自动刷新页面的方法:
   1，页面自动刷新：把如下代码加入<head>区域中
   <meta http-equiv="refresh" content="20">
   
   其中20指每隔20秒刷新一次页面.
   
   2，页面自动跳转：把如下代码加入<head>区域中
   
   <meta http-equiv="refresh" content="20;url=http://www.jb51.net">
   
   其中20指隔20秒后跳转到http://www.jb51.net页面
   
   3，页面自动刷新js版
   <script language="JavaScript">
   function myrefresh()
   {
      window.location.reload();
   }
   setTimeout('myrefresh()',1000); //指定1秒刷新一次
   </script>
   
   4，JS刷新框架的脚本语句
   //刷新包含该框架的页面用  
   <script language=JavaScript>
      parent.location.reload();
   </script>
   //子窗口刷新父窗口
   <script language=JavaScript>
       self.opener.location.reload();
   </script>
   (　或　<a href="javascript:opener.location.reload()">刷新</a>   )
   //刷新另一个框架的页面用  
   <script language=JavaScript>
      parent.另一FrameID.location.reload();
   </script>
   
   如果想关闭窗口时刷新或想开窗时刷新，在<body>中调用以下语句即可。
   
   <body onload="opener.location.reload()"> 开窗时刷新
   <body onUnload="opener.location.reload()"> 关闭时刷新
   <script language="javascript">
   window.opener.document.location.reload()
   </script>
   
   一、先来看一个简单的例子：
   
   下面以三个页面分别命名为frame.html、top.html、bottom.html为例来具体说明如何做。
   frame.html 由上(top.html)下(bottom.html)两个页面组成，代码如下： 
   
   <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
   <HTML>
   <HEAD>
   <TITLE> frame </TITLE>
   </HEAD>
   <frameset rows="50%,50%">
   <frame name=top src="top.html">
   <frame name=bottom src="bottom.html">
   </frameset>
   </HTML> 
   
   现在假设top.html (即上面的页面) 有七个button来实现对bottom.html (即下面的页面) 的刷新，可以用以下七种语句，哪个好用自己看着办了。
   top.html 页面的代码如下：
   
   <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
   <HTML>
   <HEAD>
   <TITLE> top.html </TITLE>
   </HEAD>
   <BODY>
   <input type=button value="刷新1" onclick="window.parent.frames[1].location.reload()"><br>
   <input type=button value="刷新2" onclick="window.parent.frames.bottom.location.reload()"><br>
   <input type=button value="刷新3" onclick="window.parent.frames['bottom'].location.reload()"><br>
   <input type=button value="刷新4" onclick="window.parent.frames.item(1).location.reload()"><br>
   <input type=button value="刷新5" onclick="window.parent.frames.item('bottom').location.reload()"><br>
   <input type=button value="刷新6" onclick="window.parent.bottom.location.reload()"><br>
   <input type=button value="刷新7" onclick="window.parent['bottom'].location.reload()"><br>
   </BODY>
   </HTML> 
   
   下面是bottom.html页面源代码，为了证明下方页面的确被刷新了，在装载完页面弹出一个对话框。
   
   
   <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
   <HTML>
   <HEAD>
   <TITLE> bottom.html </TITLE>
   </HEAD>
   <BODY onload="alert('我被加载了！')">
   <h1>This is the content in bottom.html.</h1>
   </BODY>
   </HTML> 
   
   解释一下： 
   
   1.window指代的是当前页面，例如对于此例它指的是top.html页面。
   2.parent指的是当前页面的父页面，也就是包含它的框架页面。例如对于此例它指的是framedemo.html。
   3.frames是window对象，是一个数组。代表着该框架内所有子页面。
   4.item是方法。返回数组里面的元素。
   5.如果子页面也是个框架页面，里面还是其它的子页面，那么上面的有些方法可能不行。
   附：
   Javascript刷新页面的几种方法：
   1 history.go(0)
   2 location.reload()
   3 location=location
   4 location.assign(location)
   5 document.execCommand('Refresh')
   6 window.navigate(location)
   7 location.replace(location)
   8 document.URL=location.href 
   
   
   
   三、java在写Servlet,Action等程序时，要操作返回页面的话（如谈出了窗口，操作完成以后，关闭当前页面，刷新父页面） 
   1 PrintWriter out = response.getWriter();
   2 out.write("<script type=\"text/javascript\">");
   3 ////子窗口刷新父窗口
   4 out.write("self.opener.location.reload();");
   5 //关闭窗口
   6 out.write("window.opener=null;");
   7 out.write("window.close();");
   8 out.write("</script>"); 
   
   四、JS刷新框架的脚本语句
   1.如何刷新包含该框架的页面用 
   <script language=JavaScript>
   parent.location.reload();
   </script> 
   
   2.子窗口刷新父窗口 
   <script language=JavaScript>
   self.opener.location.reload();
   </script> 
   
   3.如何刷新另一个框架的页面用 （上面的实例以说明了） 
   
   语句1. window.parent.frames[1].location.reload();
   语句2. window.parent.frames.bottom.location.reload();
   语句3. window.parent.frames["bottom"].location.reload();
   语句4. window.parent.frames.item(1).location.reload();
   语句5. window.parent.frames.item('bottom').location.reload();
   语句6. window.parent.bottom.location.reload();
   语句7. window.parent['bottom'].location.reload(); 
   
   4.如果想关闭窗口时刷新或者想开窗时刷新的话，在<body>中调用以下语句即可。
   <body onload="opener.location.reload()">
   开窗时刷新
   <body onUnload="opener.location.reload()">
   关闭时刷新 
   
   <script language="javascript">
   window.opener.document.location.reload()
   </script> 
   ```
   
### 24、Intellij IDEA 快捷键整理
```
【常规】

    Ctrl+Shift + Enter，语句完成
    “！”，否定完成，输入表达式时按 “！”键
    Ctrl+E，最近的文件
    Ctrl+Shift+E，最近更改的文件
    Shift+Click，可以关闭文件
    Ctrl+[ OR ]，可以跑到大括号的开头与结尾
    Ctrl+F12，可以显示当前文件的结构
    Ctrl+F7，可以查询当前元素在当前文件中的引用，然后按 F3 可以选择
    Ctrl+N，可以快速打开类
    Ctrl+Shift+N，可以快速打开文件
    Alt+Q，可以看到当前方法的声明
    Ctrl+P，可以显示参数信息
    Ctrl+Shift+Insert，可以选择剪贴板内容并插入
    Alt+Insert，可以生成构造器/Getter/Setter等
    Ctrl+Alt+V，可以引入变量。例如：new String();  自动导入变量定义
    Ctrl+Alt+T，可以把代码包在一个块内，例如：try/catch
    Ctrl+Enter，导入包，自动修正
    Ctrl+Alt+L，格式化代码
    Ctrl+Alt+I，将选中的代码进行自动缩进编排，这个功能在编辑 JSP 文件时也可以工作
    Ctrl+Alt+O，优化导入的类和包
    Ctrl+R，替换文本
    Ctrl+F，查找文本
    Ctrl+Shift+Space，自动补全代码
    Ctrl+空格，代码提示（与系统输入法快捷键冲突）
    Ctrl+Shift+Alt+N，查找类中的方法或变量
    Alt+Shift+C，最近的更改
    Alt+Shift+Up/Down，上/下移一行
    Shift+F6，重构 - 重命名
    Ctrl+X，删除行
    Ctrl+D，复制行
    Ctrl+/或Ctrl+Shift+/，注释（//或者/**/）
    Ctrl+J，自动代码（例如：serr）
    Ctrl+Alt+J，用动态模板环绕
    Ctrl+H，显示类结构图（类的继承层次）
    Ctrl+Q，显示注释文档
    Alt+F1，查找代码所在位置
    Alt+1，快速打开或隐藏工程面板
    Ctrl+Alt+left/right，返回至上次浏览的位置
    Alt+left/right，切换代码视图
    Alt+Up/Down，在方法间快速移动定位
    Ctrl+Shift+Up/Down，向上/下移动语句
    F2 或 Shift+F2，高亮错误或警告快速定位
    Tab，代码标签输入完成后，按 Tab，生成代码
    Ctrl+Shift+F7，高亮显示所有该文本，按 Esc 高亮消失
    Alt+F3，逐个往下查找相同文本，并高亮显示
    Ctrl+Up/Down，光标中转到第一行或最后一行下
    Ctrl+B/Ctrl+Click，快速打开光标处的类或方法（跳转到定义处）
    Ctrl+Alt+B，跳转到方法实现处
    Ctrl+Shift+Backspace，跳转到上次编辑的地方
    Ctrl+O，重写方法
    Ctrl+Alt+Space，类名自动完成
    Ctrl+Alt+Up/Down，快速跳转搜索结果
    Ctrl+Shift+J，整合两行
    Alt+F8，计算变量值
    Ctrl+Shift+V，可以将最近使用的剪贴板内容选择插入到文本
    Ctrl+Alt+Shift+V，简单粘贴
    Shift+Esc，不仅可以把焦点移到编辑器上，而且还可以隐藏当前（或最后活动的）工具窗口
    F12，把焦点从编辑器移到最近使用的工具窗口
    Shift+F1，要打开编辑器光标字符处使用的类或者方法 Java 文档的浏览器
    Ctrl+W，可以选择单词继而语句继而行继而函数
    Ctrl+Shift+W，取消选择光标所在词
    Alt+F7，查找整个工程中使用地某一个类、方法或者变量的位置
    Ctrl+I，实现方法
    Ctrl+Shift+U，大小写转化
    Ctrl+Y，删除当前行
    Shift+Enter，向下插入新行
    psvm/sout，main/System.out.println(); Ctrl+J，查看更多
    Ctrl+Shift+F，全局查找
    Ctrl+F，查找/Shift+F3，向上查找/F3，向下查找
    Ctrl+Shift+S，高级搜索
    Ctrl+U，转到父类
    Ctrl+Alt+S，打开设置对话框
    Alt+Shift+Inert，开启/关闭列选择模式
    Ctrl+Alt+Shift+S，打开当前项目/模块属性
    Ctrl+G，定位行
    Alt+Home，跳转到导航栏
    Ctrl+Enter，上插一行
    Ctrl+Backspace，按单词删除
    Ctrl+"+/-"，当前方法展开、折叠
    Ctrl+Shift+"+/-"，全部展开、折叠

【调试部分、编译】

    Ctrl+F2，停止
    Alt+Shift+F9，选择 Debug
    Alt+Shift+F10，选择 Run
    Ctrl+Shift+F9，编译
    Ctrl+Shift+F10，运行
    Ctrl+Shift+F8，查看断点
    F8，步过
    F7，步入
    Shift+F7，智能步入
    Shift+F8，步出
    Alt+Shift+F8，强制步过
    Alt+Shift+F7，强制步入
    Alt+F9，运行至光标处
    Ctrl+Alt+F9，强制运行至光标处
    F9，恢复程序
    Alt+F10，定位到断点
    Ctrl+F8，切换行断点
    Ctrl+F9，生成项目
    Alt+1，项目
    Alt+2，收藏
    Alt+6，TODO
    Alt+7，结构
    Ctrl+Shift+C，复制路径
    Ctrl+Alt+Shift+C，复制引用，必须选择类名
    Ctrl+Alt+Y，同步
    Ctrl+~，快速切换方案（界面外观、代码风格、快捷键映射等菜单）
    Shift+F12，还原默认布局
    Ctrl+Shift+F12，隐藏/恢复所有窗口
    Ctrl+F4，关闭
    Ctrl+Shift+F4，关闭活动选项卡
    Ctrl+Tab，转到下一个拆分器
    Ctrl+Shift+Tab，转到上一个拆分器

【重构】

    Ctrl+Alt+Shift+T，弹出重构菜单
    Shift+F6，重命名
    F6，移动
    F5，复制
    Alt+Delete，安全删除
    Ctrl+Alt+N，内联

【查找】

    Ctrl+F，查找
    Ctrl+R，替换
    F3，查找下一个
    Shift+F3，查找上一个
    Ctrl+Shift+F，在路径中查找
    Ctrl+Shift+R，在路径中替换
    Ctrl+Shift+S，搜索结构
    Ctrl+Shift+M，替换结构
    Alt+F7，查找用法
    Ctrl+Alt+F7，显示用法
    Ctrl+F7，在文件中查找用法
    Ctrl+Shift+F7，在文件中高亮显示用法

【VCS】

    Alt+~，VCS 操作菜单
    Ctrl+K，提交更改
    Ctrl+T，更新项目
    Ctrl+Alt+Shift+D，显示变化
```   


        


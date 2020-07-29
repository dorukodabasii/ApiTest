# API TEST PROJESİ
 Bu Proje Omdb API’ın girilen parametrelere göre doğru çıktıyı verip vermediğinin kontrollerinin yapıldığı bir test projesidir. 
Girilen kelime ile film adına göre filmi bulan ve ImdbId’sini alıp id ile istenilen filmi bulan bir projedir.
Bu projede Harry Potter ile "Harry Potter and the Sorcerer's Stone" filminin testini yaptım.
İsteğe göre örnekler çoğaltılabilir. Örneğin Superman ile Superman Return vb.


# KULLANILAN TEKNOLOJİLER
* **Java**
* **TestNG** 
> (TestNG, Cédric Beust tarafından oluşturulan ve JUnit ve NUnit'ten esinlenen Java programlama dili için bir test çerçevesidir. 
TestNG'nin tasarım amacı, daha güçlü ve kullanımı kolay işlevselliklerle daha geniş bir test kategorisi yelpazesini kapsamaktır.)

* **RestAsurred** 
> ( Rest Assured, rest isteklerini handle(ele almak) etmek, api seviyesinde testler yapmak ve bir çok http işlemlerini yönetebilmek için
2010 yılının Aralık ayında Johan Haleby abimiz tarafından literatüre kazandırılmış bir Java kütüphanesidir.)
* **GSON**
> ( Java kullanarak yazdığımız uygulamalarda Java nesnesinden JSON’a ve JSON’dan Java nesnesine kolaylıkla dönüşüm yapmamızı sağlayan bir kütüphanedir.)

# PROJENİN YAPISININ AÇIKLANMASI
Projede aşağıda bulunan görseldeki yapıyı kullandım. Her başlığın ne için açıldığını ve ne zaman kullanıldığını aşağıda açıklıyorum.


![proje](https://user-images.githubusercontent.com/39273991/88515767-e211ab00-cff4-11ea-8dd5-74c8dce5b197.png)

 **Common:** EndPoint interfacesi kullandım.Burada BaseURL ve ApiKeyimi tanımlıyorum.

**Configurate:** RestAssuredConfiguration sınıfımızda Rest Assured kütüphanemizin ortak kodlarını sürekli tekrar etmemesi için bu sınıfı oluşturdum.

**Entities:** Bu katmanda Entity sınıflarım var rest apiden dönen responselar JSON formatında ben bunları ObjectMapperType.GSON parametresini kullanarak deserialize ediyorum.

**Test->TestClass:** sınıfında getMovieById(String movieName,String filmName) methodu ile film adına göre arama yapıp apiden dönen filmlerden filmName'e göre arama yapıp bulunan filmin İmdbId'sini döndüren bir method yazdım.
searchFilmTest() Test Methodunda Harry Potter and the Sorcerer's Stone filminin Title, Year, Released bilgilerini kontrol ettim.


# 29.07.2020'de yapılan ekstra özellik hakkında
Yapılan iyileştirme sonrasında girilen kelime ile film ya da **oyun** adına göre bulan ve ImdbId’sini alıp id ile istenilen filmi veya oyunu bulan bir proje olmuştur.


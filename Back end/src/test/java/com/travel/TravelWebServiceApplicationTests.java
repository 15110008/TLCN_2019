package com.travel;

import com.travel.utils.UriUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
//@SpringBootTest
public class TravelWebServiceApplicationTests {

//    @Autowired
//    MapService mapService;

//    @Autowired
//    PlaceRepository placeRepository;

//    @Test
//    public void contextLoads() {
//        mapService.getPlacesContainsPlan().stream()
//                .forEach(p -> {
//                    System.out.println(p.getName());
//                });
//        mapService.getPlansWithUrlOfPlaces("da-nang").stream()
//                .forEach(p -> {
//                    System.out.println(p.getName());
//                });
//    }

//    @Test
//    public void testGetDataFromWikipedia() {
//        RestTemplate template = new RestTemplate();
//        placeRepository.findAll()
//                .stream().forEach(p -> {
//            String url = String.format("https://vi.wikipedia.org/w/api.php?action=query&prop=extracts&exintro&explaintext&titles=%s", p.getName());
//            LinkedHashMap hashMap = template.getForObject(url, LinkedHashMap.class);
//            System.out.println(url);
//            System.out.println(hashMap.get("query").toString());
//        });
//    }

//    @Test
//    public void testDetPlaceIdAndCountPlans() {
//        placeRepository.getPlaceIdAndCountPlans()
//                .forEach(objects -> {
//                    System.out.printf("%s - %s plan(s)\n", objects[0], objects[1]);
//                });
//    }

//    @Test
//    public void changeStartDate() {
//        RestTemplate template = new RestTemplate();
//        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss, d/m/yyyy");
//        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        List list = template.getForObject("http://localhost:4000/plans", List.class);
//        list.stream().forEach(o -> {
//            LinkedHashMap<String, Object> map = (LinkedHashMap) o;
//            try {
//                map.replace("startTime", sdf2.format(sdf.parse(map.get("startTime").toString())));
//                template.exchange("http://localhost:4000/plans/" + map.get("id"), HttpMethod.PUT, new HttpEntity<>(map), LinkedHashMap.class);
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//    }

    @Test
    public void testEncode() {
        String output = UriUtils.encodingUri("Phím tắt luôn giúp chúng ta thao tác nhanh và thuận tiện hơn khi sử dụng thiết bị của mình. Windows 10 có nhiều tính năng và thiết lập mới hấp dẫn, sẽ rất thú vị khi bạn biết dùng phím tắt để khai thác hệ điều hành này một các tối ưu và dễ dàng hơn.\n" +
                "Thegioididong đã có một bài giới thiệu về phím tắt đối với tính năng phân chia cửa sổ và màn hình ảo nên bài này sẽ không nhắc lại. Bạn có thể xem lại >>>tại đây<>\n" +
                "\n" +
                "*Lưu ý: Số lượng phím tắt đề cập trong bài là khá nhiều, để tránh “tẩu quả nhập ma” trí nhớ, bạn nên lưu địa chỉ bài viết này lại hoặc đưa vào trong danh sách yêu thích của trình duyệt để “gối đầu giường” đọc và áp dụng lần lượt về sau.");
        System.out.println(output);
    }
}

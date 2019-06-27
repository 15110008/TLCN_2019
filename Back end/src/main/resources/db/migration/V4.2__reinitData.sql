DELETE FROM plans_places;
DELETE FROM plans;

UPDATE places SET id = 99, latitude = 21.028472,longitude = 105.854167 WHERE id = 0;
UPDATE places SET id = 0 WHERE id = 1;
UPDATE places SET id = 1 WHERE id = 2;
UPDATE places SET id = 2 WHERE id = 3;
UPDATE places SET id = 3 WHERE id = 4;
UPDATE places SET id = 4 WHERE id = 5;
UPDATE places SET id = 5 WHERE id = 6;
UPDATE places SET id = 6 WHERE id = 7;
UPDATE places SET id = 7 WHERE id = 8;
UPDATE places SET id = 8 WHERE id = 9;
UPDATE places SET id = 9 WHERE id = 10;
UPDATE places SET id = 10 WHERE id = 11;
UPDATE places SET id = 11 WHERE id = 12;
UPDATE places SET id = 12 WHERE id = 13;
UPDATE places SET id = 13 WHERE id = 14;
UPDATE places SET id = 14 WHERE id = 15;
UPDATE places SET id = 15 WHERE id = 16;
UPDATE places SET id = 16 WHERE id = 17;
UPDATE places SET id = 17 WHERE id = 18;
UPDATE places SET id = 18 WHERE id = 19;
UPDATE places SET id = 19 WHERE id = 20;
UPDATE places SET id = 20 WHERE id = 21;
UPDATE places SET id = 21 WHERE id = 22;
UPDATE places SET id = 22 WHERE id = 23;
UPDATE places SET id = 23 WHERE id = 24;
UPDATE places SET id = 24 WHERE id = 25;
UPDATE places SET id = 25 WHERE id = 26;
UPDATE places SET id = 26 WHERE id = 27;
UPDATE places SET id = 27 WHERE id = 28;
UPDATE places SET id = 28 WHERE id = 29;
UPDATE places SET id = 29 WHERE id = 30;
UPDATE places SET id = 30 WHERE id = 31;
UPDATE places SET id = 31 WHERE id = 32;
UPDATE places SET id = 32 WHERE id = 33;
UPDATE places SET id = 33 WHERE id = 34;
UPDATE places SET id = 34 WHERE id = 35;
UPDATE places SET id = 35 WHERE id = 36;
UPDATE places SET id = 36 WHERE id = 37;
UPDATE places SET id = 37 WHERE id = 38;
UPDATE places SET id = 38 WHERE id = 39;
UPDATE places SET id = 39 WHERE id = 40;
UPDATE places SET id = 40 WHERE id = 41;
UPDATE places SET id = 41 WHERE id = 42;
UPDATE places SET id = 42 WHERE id = 43;
UPDATE places SET id = 43 WHERE id = 44;
UPDATE places SET id = 44 WHERE id = 45;
UPDATE places SET id = 45 WHERE id = 46;
UPDATE places SET id = 46 WHERE id = 47;
UPDATE places SET id = 47 WHERE id = 48;
UPDATE places SET id = 48 WHERE id = 49;
UPDATE places SET id = 49 WHERE id = 50;
UPDATE places SET id = 50 WHERE id = 51;
UPDATE places SET id = 51 WHERE id = 52;
UPDATE places SET id = 52 WHERE id = 53;
UPDATE places SET id = 53 WHERE id = 54;
UPDATE places SET id = 54 WHERE id = 55;
UPDATE places SET id = 55 WHERE id = 56;
UPDATE places SET id = 56 WHERE id = 57;
UPDATE places SET id = 57 WHERE id = 58;
UPDATE places SET id = 58 WHERE id = 59;
UPDATE places SET id = 59 WHERE id = 60;
UPDATE places SET id = 60 WHERE id = 61;
UPDATE places SET id = 61 WHERE id = 62;

UPDATE places set parent_id = 99;

INSERT INTO places(id,name,url,latitude,longitude, parent_id) VALUES (62,'Phú Quốc','phu-quoc',10.292315,103.984091,31);
INSERT INTO places(id,name,url,latitude,longitude, parent_id) VALUES (63,'Tp Mỹ Tho','my-tho',10.375690, 106.342565,56);
INSERT INTO places(id,name,url,latitude,longitude, parent_id) VALUES (64,'Tp Rạch Giá','rach-gia',10.024148, 105.086714,31);
INSERT INTO places(id,name,url,latitude,longitude, parent_id) VALUES (65,'Tuy Hoà','tuy-hoa',13.105936, 109.299772,43);
INSERT INTO places(id,name,url,latitude,longitude, parent_id) VALUES (66,'Quy Nhơn','quy-nhon',13.780879, 109.218773,6);
INSERT INTO places(id,name,url,latitude,longitude, parent_id) VALUES (67,'Tp Hội An','hoi-an',15.879982, 108.343256,45);
INSERT INTO places(id,name,url,latitude,longitude, parent_id) VALUES (68,'Tp Đà Lạt','da-lat',11.940848, 108.455197,34);
INSERT INTO places(id,name,url,latitude,longitude, parent_id) VALUES (69,'Tp Nha Trang','nha-trang',12.239291, 109.193614,30);
INSERT INTO places(id,name,url,latitude,longitude, parent_id) VALUES (70,'Tp Phan Thiết','phan-thiet',10.978847, 108.267455,9);
INSERT INTO places(id,name,url,latitude,longitude, parent_id) VALUES (71,'Tp Hạ Long','ha-long',20.969192, 107.037380,47);
INSERT INTO places(id,name,url,latitude,longitude, parent_id) VALUES (72,'Tt Sa Pa','sa-pa',22.360265, 103.817395,36);

INSERT INTO plans(id,name,url,start_time,number_of_slot,number_of_reserved_slot,adult_price,child_price,tour_id) VALUES
  (1,'DU LỊCH TẾT NGUYÊN ĐÁN TẠI CÁI BÈ - CẦN THƠ - CHÂU ĐỐC - HÀ TIÊN','du-lich-tet-nguyen-dan-tai-cai-be-can-tho-chau-doc-ha-tien','2019-01-06 08:02:14',30,0,4429000,2215000,72)
  ,(2,'DU LỊCH MỸ THO - CẦN THƠ - CÀ MAU - BẠC LIÊU - SÓC TRĂNG','du-lich-my-tho-can-tho-ca-mau-bac-lieu-soc-trang','2018-01-22 08:12:43',30,0,3529000,1765000,74)
  ,(3,'DU LỊCH [ĐÓN NĂM MỚI 2019] CẦN THƠ- CỒN SƠN - CHỢ NỔI CÁI RĂNG','du-lich-don-nam-moi-2019-can-tho-con-son-cho-noi-cai-rang','2018-01-31 10:12:39',30,0,1979000,990000,71)
  ,(4,'DU LỊCH TẾT NGUYÊN ĐÁN TẠI SA ĐÉC - CHÂU ĐỐC- NÚI CẤM','du-lich-tet-nguyen-dan-tai-sa-dec-chau-doc-nui-cam','2019-01-06 10:02:39',30,0,1979000,990000,70)
  ,(5,'DU LỊCH PHÚ QUỐC [ĐÓN NĂM MỚI 2019]','du-lich-phu-quoc-don-nam-moi-2019','2018-01-30 06:12:32',30,0,3190000,1700000,64)
  ,(6,'DU LỊCH PHÚ QUỐC CÂU CÁ - NGẮM SAN HÔ [VIETJET]','du-lich-phu-quoc-cau-ca-ngam-san-ho-vietjet','2019-01-10 06:01:17',30,0,3929000,2729000,61)
  ,(7,'DU LỊCH PHÚ QUỐC [KHÁM PHÁ ĐẢO NGỌC PHÚ QUỐC]','du-lich-phu-quoc-kham-pha-dao-ngoc-phu-quoc','2019-01-15 06:01:17',30,0,7350000,5900000,65)
  ,(8,'DU LỊCH QUY NHƠN - KỲ CO - EO GIÓ - TUY HÒA','du-lich-quy-nhon-ky-co-eo-gio-tuy-hoa','2018-01-18 06:12:56',30,0,5879000,3839000,53)
  ,(9,'DU LỊCH [ĐÓN NĂM MỚI 2019] ĐÀ NẴNG- SUỐI KHOÁNG NÓNG THẦN TÀI - HỘI AN - BÀ NÀ - CẦU VÀNG - HUẾ','du-lich-don-nam-moi-2019-da-nang-suoi-khoang-nong-than-tai-hoi-an-ba-na-cau-vang-hue','2019-01-14 06:01:39',30,0,5039000,3339000,48)
  ,(10,'DU LỊCH ĐÀ NẴNG - HỘI AN - ĐỘNG THIÊN ĐƯỜNG - HUẾ - BÀ NÀ 5 NGÀY','du-lich-da-nang-hoi-an-dong-thien-duong-hue-ba-na-5-ngay','2019-01-15 06:01:39',30,0,6239000,3939000,51)
  ,(11,'DU LỊCH [ĐÓN NĂM MỚI 2019] ĐÀ NẴNG- SUỐI KHOÁNG NÓNG THẦN TÀI - HỘI AN - BÀ NÀ - CẦU VÀNG - HUẾ','du-lich-don-nam-moi-2019-da-nang-suoi-khoang-nong-than-tai-hoi-an-ba-na-cau-vang-hue','2019-01-15 06:01:39',30,0,9029000,6535000,47)
  ,(12,'DU LỊCH BUÔN MA THUỘT - BUÔN ĐÔN - PELIKU - THỦY ĐIỆN IALY','du-lich-buon-ma-thuot-buon-don-peliku-thuy-dien-ialy','2019-01-15 06:01:39',30,0,4579000,2290000,42)
  ,(13,'DU LỊCH ĐÀ LẠT - TRANG TRẠI RAU & HOA -ĐƯỜNG HẦM ĐIÊU KHẮC - CÀ PHÊ MÊ LINH - CHÙA LINH ẤN','du-lich-da-lat-trang-trai-rau-hoa-duong-ham-dieu-khac-ca-phe-me-linh-chua-linh-an','2019-01-06 08:01:24',30,0,3179000,1590000,39)
  ,(14,'DU LỊCH TẾT NGUYÊN ĐÁN TẠI ĐÀ LẠT - TRANG TRẠI RAU & HOA - ĐƯỜNG HẦM ĐIÊU KHẮC- CÀ PHÊ MÊ LINH - CHÙA LINH ẨN','du-lich-tet-nguyen-dan-tai-da-lat-trang-trai-rau-hoa-duong-ham-dieu-khac-ca-phe-me-linh-chua-linh-an','2019-01-06 08:01:24',30,0,7429000,5295000,37)
  ,(15,'DU LỊCH TẾT NGUYÊN ĐÁN TẠI ĐÀ LẠT - TRANG TRẠI RAU & HOA - ĐƯỜNG HẦM ĐIÊU KHẮC- NÚI LANG BIANG','du-lich-tet-nguyen-dan-tai-da-lat-trang-trai-rau-hoa-duong-ham-dieu-khac-nui-lang-biang','2019-01-06 08:01:02',30,0,3179000,1590000,38)
  ,(16,'DU LỊCH TẾT NGUYÊN ĐÁN TẠI ĐÀ LẠT - TRANG TRẠI RAU & HOA - ĐƯỜNG HẦM ĐIÊU KHẮC KHU DU LỊCH LANGBIANG (BAY VN)','du-lich-tet-nguyen-dan-tai-da-lat-trang-trai-rau-hoa-duong-ham-dieu-khac-khu-du-lich-langbiang-bay-vn','2019-01-10 06:01:02',30,0,3179000,1590000,36)
  ,(17,'DU LỊCH TẾT NGUYÊN ĐÁN TẠI NHA TRANG - HÒN LAO','du-lich-tet-nguyen-dan-tai-nha-trang-hon-lao','2019-01-10 06:01:02',30,0,43799000,2159000,32)
  ,(18,'DU LỊCH PHAN THIẾT ĐÓN NĂM MỚI 2019 [MŨI NÉ - TÀ KÚ]','du-lich-phan-thiet-don-nam-moi-2019-mui-ne-ta-ku','2019-01-07 08:02:46',30,0,3679000,1840000,26)
  ,(19,'DU LỊCH TẠI PHAN THIẾT - MŨI NÉ','du-lich-tai-phan-thiet-mui-ne','2019-01-07 09:02:46',30,0,3339000,1670000,24)
  ,(20,'DU LỊCH PHAN THIẾT - MŨI NÉ - LÀNG CHÀI XƯA','du-lich-phan-thiet-mui-ne-lang-chai-xua','2019-01-16 07:02:46',30,0,3339000,1670000,23)
  ,(21,'DU LỊCH THANH HÓA- PÙ LUÔNG - SUỐI CÁ THẦN CẨM LƯƠNG - DU THUYỀN TRÊN SÔNG MÃ','du-lich-thanh-hoa-pu-luong-suoi-ca-than-cam-luong-du-thuyen-tren-song-ma','2019-01-16 07:02:46',30,0,4339000,1670000,22)
  ,(22,'DU LỊCH THANH HÓA - SUỐI CÁ THẦN CẨM LƯƠNG - VƯỜN THÚ SAFARI - ĐẢO CHÈ THANH CHƯƠNG','du-lich-thanh-hoa-suoi-ca-than-cam-luong-vuon-thu-safari-dao-che-thanh-chuong','2019-01-16 07:02:46',30,0,3339000,1670000,21)
  ,(23,'DU LỊCH TẾT NGUYÊN ĐÁN TẠI HÀ NỘI - NAM ĐỊNH - NINH BÌNH - HẠ LONG (BAY VN','du-lich-tet-nguyen-dan-tai-ha-noi-nam-dinh-ninh-binh-ha-long-bay-vn','2019-01-06 06:02:08',30,0,12069000,8930500,20)
  ,(24,'DU LỊCH TẾT NGUYÊN ĐÁN TẠI HÀ NỘI - LÀO CAI - SAPA - HẠ LONG (BAY VN)','du-lich-tet-nguyen-dan-tai-ha-noi-lao-cai-sapa-ha-long-bay-vn','2019-01-06 06:02:29',30,0,13519000,9656000,19)
  ,(25,'DU LỊCH TẾT TẠI HẠ LONG - HÀ NỘI - SAPA - LÀO CAI - ĐỀN HÙNG','du-lich-tet-tai-ha-long-ha-noi-sapa-lao-cai-den-hung','2019-01-06 06:02:29',30,0,11729000,8465000,18)
  ,(26,'DU LỊCH TẾT NGUYÊN ĐÁN TẠI HÀ NỘI - NINH BÌNH - HẠ LONG','du-lich-tet-nguyen-dan-tai-ha-noi-ninh-binh-ha-long','2019-01-06 06:02:05',30,0,10179000,7690000,13)
  ,(27,'DU LỊCH [MÙA HOA TAM GIÁC MẠCH] HÀ GIANG - ĐỒNG VĂN - CAO BẰNG - BẮC KẠN','du-lich-mua-hoa-tam-giac-mach-ha-giang-dong-van-cao-bang-bac-kan','2019-01-06 06:01:05',30,0,8979000,5925000,7)
  ,(28,'DU LỊCH TẾT NGUYÊN ĐÁN TẠI HÀ NỘI - HỒ BA BỂ - BẢN GIỐC - PÁC BÓ - LẠNG SƠN (BAY VN)','du-lich-tet-nguyen-dan-tai-ha-noi-ho-ba-be-ban-gioc-pac-bo-lang-son-bay-vn','2019-01-06 06:02:05',30,0,14019000,9906000,5)
  ,(29,'DU LỊCH TẾT NGUYÊN ĐÁN TẠI PHÚ THỌ- HÀ GIANG - ĐỒNG VĂN- LŨNG CÚ - HÀ NỘI (BAY VN)','du-lich-tet-nguyen-dan-tai-phu-tho-ha-giang-dong-van-lung-cu-ha-noi-bay-vn','2019-01-06 06:02:05',30,0,13719000,9756000,4);

INSERT INTO plans_places(plan_id,place_id) VALUES
  (1,56)
  ,(1,11)
  ,(1,0)
  ,(1,31)
  ,(2,63)
  ,(2,5)
  ,(2,11)
  ,(2,10)
  ,(2,3)
  ,(2,49)
  ,(3,11)
  ,(4,18)
  ,(4,0)
  ,(5,11)
  ,(5,62)
  ,(5,64)
  ,(6,62)
  ,(7,62)
  ,(8,66)
  ,(8,65)
  ,(9,13)
  ,(9,55)
  ,(9,67)
  ,(10,13)
  ,(10,67)
  ,(10,55)
  ,(10,44)
  ,(11,13)
  ,(11,67)
  ,(12,14)
  ,(13,68)
  ,(14,68)
  ,(15,68)
  ,(15,69)
  ,(16,68)
  ,(16,69)
  ,(17,68)
  ,(17,69)
  ,(18,70)
  ,(19,70)
  ,(20,70)
  ,(21,54)
  ,(22,54)
  ,(23,22)
  ,(23,38)
  ,(23,71)
  ,(23,40)
  ,(24,22)
  ,(24,72)
  ,(24,36)
  ,(24,71)
  ,(25,22)
  ,(25,72)
  ,(25,36)
  ,(25,71)
  ,(26,22)
  ,(26,71)
  ,(26,40)
  ,(27,20)
  ,(27,12)
  ,(27,2)
  ,(28,22)
  ,(28,35)
  ,(28,12)
  ,(29,42)
  ,(29,20)
  ,(29,22);


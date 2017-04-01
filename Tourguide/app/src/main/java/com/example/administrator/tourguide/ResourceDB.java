package com.example.administrator.tourguide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/28.
 */

public class ResourceDB {
    private List<ListContentDB> historicalsites = new ArrayList<ListContentDB>();
    private List<ListContentDB> park = new ArrayList<ListContentDB>();
    private List<ListContentDB> restaurant = new ArrayList<ListContentDB>();
    private List<ListContentDB> hotel = new ArrayList<ListContentDB>();

    private ListContentDB[] PreHistoricalsites = {
            new ListContentDB(R.drawable.huanghelou,
                    "黄鹤楼\n\n地址：湖北省武汉市武昌区蛇山西山坡特1号。",
                    "评分：",
                    "门票价格：¥75",
                    "简介：黄鹤楼位于湖北省武汉市长江南岸的武昌蛇山之巅，" +
                            "为国家5A级旅游景区，享有“天下江山第一楼“、”天下绝景“之称。",
                    (float) 4.5),
            new ListContentDB(R.drawable.guiyuansi,
                    "归元寺\n\n地址：湖北省武汉市汉阳区翠微路20号。",
                    "评分：",
                    "门票价格：¥20",
                    "简介：归元禅寺位于湖北省武汉市汉阳区归元寺路，" +
                            "由白光法师于清顺治15年（公元1658年）兴建。" +
                            "占地10公顷，有殿舍200余间，各类佛教经典7000余卷。",
                    (float) 4.2),
            new ListContentDB(R.drawable.guqintai,
                    "古琴台\n\n地址：湖北省武汉市汉阳区汉阳琴台大道10号",
                    "评分：",
                    "门票价格：¥15",
                    "简介：古琴台又名俞伯牙台，始建于北宋，重建于清嘉庆初年（公元1796年），" +
                            "位于湖北省武汉市汉阳龟山西脚下美丽的月湖之滨，东对龟山、北临月湖，" +
                            "是中国著名的音乐文化古迹、湖北省重点文物保护单位、武汉市著名的文物旅游景观之一" +
                            "，有“天下知音第一台”之称。",
                    (float) 4.3),
            new ListContentDB(R.drawable.qingchuange,
                    "晴川阁\n\n地址：湖北省武汉市汉阳区洗马长街86号",
                    "评分：",
                    "门票价格：¥0",
                    "简介：晴川阁，位于中国湖北省武汉市汉阳龟山东麓禹功矶上，" +
                            "始建于明代嘉靖26年至28（公元1547—1549）年，" +
                            "为汉阳太守范之箴在修葺禹稷行宫（原为禹王庙）时所增建，" +
                            "得名于唐朝诗人崔颢“晴川历历汉阳树，芳草萋萋鹦鹉洲”诗句。",
                    (float) 4.4)};

    private ListContentDB[] PrePark = {
            new ListContentDB(
                    R.drawable.hanyanggongyuan,
                    "汉阳公园\n\n地址：武汉市汉阳区汉阳大道74号",
                    "评分：",
                    "门票价格：¥0",
                    "简介：汉阳公园位于武汉市汉阳区，近龟山风景区。" +
                            "公园始建于1957年，面积约2.6万多平方米，园内环境优美，林木茂密。" +
                            "公园内主要有文汇堂、语香楼、风翼亭、虚心亭、丛笑亭、送暖亭、眺虹轩等建筑，亭台水榭，繁花似锦。" +
                            "这里最大的特色便是花卉，每到春天牡丹、芍药、杜鹃、月季等竞相开放，一派欣欣向荣的景象。",
                    (float) 4.2),
            new ListContentDB(
                    R.drawable.jiefanggongyuan,
                    "解放公园\n\n地址：武汉江岸区解放大道1861号",
                    "评分：",
                    "门票价格：¥0",
                    "简介：解放公园始建于1952年，前身为英、法、俄、德、日、比六国洋商跑马场，俗称西商跑马场。" +
                            "公园建成于1955年，正好是武汉解放六周年，因此命名为“解放公园”，是武汉市区最大的自然生态公园。" +
                            "公园林木葱茏、绿草成茵、山水相依、鸟语花香。" +
                            "公园东部有绿茵广场、盆景园、荷花池、睡莲塘、朝梅岭、苏联空军志愿军烈士墓等；" +
                            "西部是占地约13公顷的柳林区，现保存着高大的杨柳、箭杆杨、毛白杨等乔木千余株。",
                    (float) 4.7),
            new ListContentDB(
                    R.drawable.zhongshangongyuan,
                    "中山公园\n\n地址：武汉市江汉区解放大道1265号",
                    "评分：",
                    "门票价格：¥0",
                    "简介：武汉中山公园是全国百家历史名园之一，始建于1923年，是武汉市历史悠久的文化休憩公园。" +
                            "公园集游览、观赏、文化、娱乐、饮食、游艺等多项服务功能于一身，绿化率达91%，" +
                            "有植物219多个品种，绿树成荫，空气清爽。公园分前、中、后三个景区。",
                    (float) 4.4),
            new ListContentDB(
                    R.drawable.hankoujiangtangongyuan,
                    "汉口江滩公园\n\n地址：武汉市江岸区沿江大道",
                    "评分：",
                    "门票价格：¥0",
                    "简介：汉口江滩公园上起武汉客运港，下至丹水池后湖船厂，全长7公里，分三期进行规划建设。" +
                            "第一期工程从武汉客运港至粤汉码头长1.04公里，绿地面积14万平方米，" +
                            "以大面积绿化和滨江公共休闲活动空间为主，展示城市景观，" +
                            "第二期工程（武汉长江二桥至三阳路段）于2003年9月28日对市民开放，形成3.4公里长的“绿化滨江长廊”。",
                    (float) 4.3)
    };

    private ListContentDB[] PreRestaurant= {
            new ListContentDB(
                    R.drawable.liangliangzhengxia,
                    "靓靓蒸虾(雪松路总店)\n\n地址：武汉市江汉区万松园雪松路69号",
                    "评分：",
                    "人均价格：¥120",
                    "简介：武汉吃虾的好去处，餐厅开了有些许年头，凭借着特色菜品和口味，" +
                            "一直有着不错的口碑，上过电视节目《天天向上》后，人气更是飞涨。" +
                            "这里最有特色的菜品是靓靓蒸虾，个头非常大，那清甜或麻辣的口味让人回味起来就口水直流；" +
                            "油焖虾也很不错。店内装修较为简单，但冲着那股鲜美劲儿，绝对值得一去。",
                    (float) 4.5),
            new ListContentDB(
                    R.drawable.hujinjiulou,
                    "湖锦酒楼(光谷店)\n\n地址：武汉市洪山区珞喻路1037号华中科技大学洪山校区88号光谷体育馆对面(近光谷路)",
                    "评分：",
                    "人均价格：¥99",
                    "简介：武汉大名鼎鼎的湖北菜餐厅，辣得跳是店里的自创招牌菜，牛蛙肉质特别鲜嫩，" +
                            "刚入口时都感受不到辣，结果后劲十足，但却让人还是忍不住想再来一块。" +
                            "蒜香排骨、葱烧武昌鱼也很值得品尝。店内环境宽敞，装修也比较气派，服务员也都很热情。",
                    (float) 4.5),
            new ListContentDB(
                    R.drawable.kouweitang,
                    "口味堂(珞喻路总店)\n\n地址：武汉市洪山区珞喻路530号",
                    "评分：",
                    "人均价格：¥115",
                    "简介：装修的很有中国风的一家湘菜餐馆，在武汉也开了一定年头，口碑一直很好。店内明亮宽敞，" +
                            "出品的湘菜看上去都很精致，卖相特别好，而且分量十足。" +
                            "农家小炒肉、口味鱼头、手撕包菜等都是香辣诱人。店内的服务也很值得称赞。",
                    (float) 4.8),
            new ListContentDB(
                    R.drawable.xiashishaguo,
                    "夏氏砂锅(万松园店)\n\n地址：武汉市江汉区雪松路安全村231号",
                    "评分：",
                    "人均价格：¥70",
                    "简介：武汉人与游客都热衷的饭店，古色古香的装饰，门面看着不大，" +
                            "里面却别有洞天。一品海鲜粥是店里的特色，味道十分鲜美，" +
                            "用料新鲜分量足，牛蛙煲、沸腾虾、你没吃过我的豆腐等也是顾客们必点的菜品。",
                    (float) 4.8),
    };
    private ListContentDB[] PreHotel= {
            new ListContentDB(
                    R.drawable.weiyena,
                    "维也纳国际酒店\n\n地址：武汉市江岸区黄浦路68号上东汇广场",
                    "评分：",
                    "人均价格：¥264",
                    "简介：维也纳国际酒店（长江二桥店）隶属于维也纳酒店集团旗下国际酒店品牌之一，" +
                            "酒店位于武汉市汉口武汉大道商会、金融核心地带，" +
                            "毗邻水利部长江水利委员会、解放军一六一医院等大型事业单位及解放公园、武汉天地、汉口江滩等知名景点。" +
                            "酒店楼高23层，共有215间客房，酒店配有大型会议室、中西餐厅、娱乐休闲、大型商场、大型停车场、银行等配套设施。" ,
                    (float) 4.7),
            new ListContentDB(
                    R.drawable.niubinkai,
                    "武汉纽宾凯徐东国际酒店\n\n地址：武昌区友谊大道506号",
                    "评分：",
                    "人均价格：¥365",
                    "简介：武汉纽宾凯徐东国际酒店位于武汉金融商业中心徐东商圈，" +
                            "地段优越、交通便捷、周边商务办公区域密集、新世界百货、徐东销品茂购物广场、休闲中心等场所步行5分钟到达。" +
                            "酒店拥有各类全新舒适客房、现代化商务设施、外观设计时尚、简洁流畅、宽敞舒适、环保装修，" +
                            "酒店所有区域均提供免费wifi、房间配备免费高速上网、咖啡、矿泉水、室内保险箱、独立控制中央空调，" +
                            "国际国内直拨电话、迷你吧、特色阅读灯。酒店同时拥有不同规模的会议室，可承接各类专业性会议。 " ,
                    (float) 4.6),
            new ListContentDB(
                    R.drawable.danfengbailu,
                    "武汉丹枫白露酒店\n\n地址：江岸区三阳路118号",
                    "评分：",
                    "人均价格：¥570",
                    "简介：酒店开业时间2011年12月1日，楼高28层，客房总数375间（套）。" +
                            " 2012-2-10起，通过艺龙预订并入住的客人可享受“24小时入住制”，" +
                            "即无论何时入住，均可享受连续入住满24小时按1天计算房费的优惠。",
                    (float) 4.7),
            new ListContentDB(
                    R.drawable.xierdun,
                    "武汉世茂希尔顿酒店 \n\n地址：汉阳区滨江大道190号",
                    "评分：",
                    "人均价格：¥781",
                    "简介：武汉世茂希尔顿酒店地处长江江畔，毗邻晴川阁、古琴台、归元寺等著名旅游景点和鹦鹉洲长江大桥等地标建筑，" +
                            "与黄鹤楼隔江相望，是长江边上一颗璀璨的明珠。酒店紧靠交通枢纽，可快捷到达机场和火车站." +
                            "入住武汉世茂希尔顿酒店纵览无限长江风光和现代都会的飒爽风姿，" +
                            "体验细致入微的贴心服务。338间时尚典雅的客房，配备全日制餐厅、中餐厅、法餐厅、行政酒廊及大堂吧，" +
                            "完善的会议及娱乐设施包括1188平方米的大宴会厅及10间多功能会议室。",
                    (float) 4.4),
    };

    public ResourceDB() {
        for (int i = 0; i < PreHistoricalsites.length; i++)
            historicalsites.add(PreHistoricalsites[i]);
        for (int i = 0; i < PrePark.length; i++)
           park.add(PrePark[i]);
        for (int i = 0; i < PreRestaurant.length; i++)
            restaurant.add(PreRestaurant[i]);
        for (int i = 0; i < PreHotel.length; i++)
            hotel.add(PreHotel[i]);
    }

    public List<ListContentDB> getHistoricalsites() {
        return historicalsites;
    }

    public List<ListContentDB> getPark() {
        return park;
    }

    public List<ListContentDB> getRestaurant() {
        return restaurant;
    }

    public List<ListContentDB> getHotel() {
        return hotel;
    }



}

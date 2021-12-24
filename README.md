# LoveStoryPaperPlane
>本程式已java作為主要程式語言開發，並參考憤怒鳥而開發出一款以紙飛機為主體的遊戲。

## 檔案性質
**GameFrame.java**
> ### 遊戲主體程式 

- * *GameFrame.java* * : 遊戲主程式，也是主畫面視窗的物件。
- * *GameObject.java* * : 遊戲中所有互動物件之父類別。
- * *GamePanel.java* * : 遊戲關卡中圖片繪製與主要操作的版面。
- * *GameThread.java* * : 遊戲中控制紙飛機飛行地Thread物件。
- * *GameUtil.java* * : 用於提取圖片素材並傳換成Image物件。
- * *LevelFrame.java* * : 於選定關卡與關卡遊玩所使用之視窗物件。
- * *Obsacle.java* * : 遊戲中的障礙物物件，與紙飛機碰撞後會倒塌，繼承GameObject。
- * *Papperplane.java* * : 遊戲中的紙飛機物件，可與障礙物發生碰撞，並使其倒塌攻擊目標，繼承GameObject。
- * *Slingshot.java* * : 遊戲中玩家主只要操作之物件，可決定計算玩家拖曳滑鼠的角度與距離，以此決定紙飛機發射角度與力度，繼承GameObject。
- * *Target.java* * : 遊戲中達成勝利條件的主要目標，繼承GameObject。

> ### 遊戲圖片素材
本遊戲所有圖片素材皆放置於images資料夾中。
- 

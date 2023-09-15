
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class WordCRUD  implements ICRUD {

    ArrayList<Word> List;
    Scanner s;
    final String fname = "Dictionary.txt";

    WordCRUD(Scanner s){
        List = new ArrayList<>() ;
        this.s = s;
    }



    @Override
    public Object add() {
        System.out.print("=> 난이도(1,2,3) & 새 단어 입력 : ");
        int level = s.nextInt();
        String word = s.nextLine();

        System.out.print("뜻 입력 : ");
        String meaning = s.nextLine();


        return new Word(0,level, word, meaning);
    }


    public void addItem() {
        Word one = (Word)add();
        List.add(one);
        System.out.println("새 단어가 단어장에 추가되었습니다");


    }

    @Override
    public int update(Object obj) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int delete(Object obj) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void selectOne(int id) {
        // TODO Auto-generated method stub

    }
    public void ListAll() {
        System.out.println("----------------------------");
        for(int i = 0; i< List.size(); i++) {
            System.out.print((i+1) + " ");
            System.out.println(List.get(i).toString());
        }
        System.out.println("----------------------------");
    }
    public ArrayList<Integer> ListAll(String keyword) {
        int j = 0;
        ArrayList<Integer> idlist = new ArrayList<>();
        System.out.println("----------------------------");
        for(int i = 0; i< List.size(); i++) {
            String word = List.get(i).getWord();
            if(!word.contains(keyword))continue;
            System.out.print((j+1) + " ");
            System.out.println(List.get(i).toString());
            idlist.add(i);
            j++;
        }
        System.out.println("----------------------------");
        return idlist;
    }

    public void ListAll(int Level){
        int j = 0;
        System.out.println("----------------------------");
        for(int i = 0; i< List.size(); i++) {
            int iLevel = List.get(i).getLevel();
            if(iLevel != Level)continue;
            System.out.print((j+1) + " ");
            System.out.println(List.get(i).toString());
            j++;
        }
        System.out.println("----------------------------");
    }

    public void updateItem() {
        System.out.println("==> 수정할 단어 검색 : ");
        String keyword = s.next();
        ArrayList<Integer> idlist = this.ListAll(keyword);
        System.out.println("==> 수정할 번호 선택 : ");
        int id = s.nextInt();
        s.nextLine();
        System.out.println("==> 뜻 입력 : ");
        String meaning = s.nextLine();
        Word word = List.get(idlist.get(id-1));
        word.setMeaning(meaning);
        System.out.println("단어가 수정되었습니다.");
    }


    public void deleteItem() {
        System.out.println("==> 삭제할 단어 검색 : ");
        String keyword = s.next();
        ArrayList<Integer> idlist = this.ListAll(keyword);
        System.out.println("==> 삭제할 번호 선택 : ");
        int id = s.nextInt();
        s.nextLine();

        System.out.println("==> 정말로 삭제하실래요?(Y/N)");

        String ans = s.next();
        if(ans.equalsIgnoreCase("y"))
        {

            List.remove((int)idlist.get(id-1));
            System.out.println("단어가 삭제되었습니다");
        } else
            System.out.println("취소되었습니다.");
    }



    public void LoadFile() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fname));
            String Line;
            int count = 0;
            while (true) {
                Line = br.readLine();
                if (Line == null) break;
                String data[] = Line.split("\\|");
                int level = Integer.parseInt((data[0]));
                String word = data[1];
                String meaning = data[2];
                List.add(new Word(0, level, word, meaning));
                count++;
            }
            br.close();
            System.out.println("==>" + count+ "개 로딩 완료!!!");

        } catch (IOException e) {
            e.printStackTrace();

        }


    }
    public void saveFile() {
        try {
            PrintWriter pr = new PrintWriter(new FileWriter(fname));
            for(Word one : List){
                pr.write(one.toFileString() + "\n");

            }
            pr.close();
            System.out.println("==> 데이터 저장 완료 !!!");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public void searchLevel() {
        System.out.println("=> 원하는 레벨은? (1~3) ");
        int Level = s.nextInt();
        ListAll(Level);
    }

    public void searchWord() {
        System.out.println("=> 원하는 단어는? ");
        String keyword = s.next();
        ListAll(keyword);
    }
}




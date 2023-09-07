import java.util.Scanner;
import java.util.ArrayList;

public class WordCRUD  implements ICRUD {

    ArrayList<Word> List;
    Scanner s;


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
}






/**
 * TestSymTable
 *
 * This is a class whose sole purpose is to test the Sym and SymTableClass, which
 * provides the following operations:
 *      Sym.java
 *       Sym(String type)   -- set type
 *       String getType())  -- return type of Sym
 *       String toString()  -- returns type of Sym
 *      SymTable.java
 *       empty constructor  -- Initalize SymTable and set first element to Empty HashMap
 *       void addDecl(String name, Sym sym) -- add the given name and sym to the first HashMap in the list.
 *       Sym lookupLocal    -- If the first HashMap in the list contains name as a key, return the associated Sym; otherwise, return null.
 *       Sym lookupGlobal   -- If any HashMap in the list contains name as a key, return the first associated Sym
 *       void removeScope   -- Remove HashMap from head of SymTable
 * It produces output ONLY if a test fails.
 */

public class P1TEST {
    public static void main(String [] args) {        
        
        //test data
        String testName1 = "test1";
        String type1 = "type1";
        

        String testName2 = "test2";
        String type2 = "type2";
        

        String testName3 = "test3";
        String type3 = "type3";
       

        String testName4 = "test4";
        String type4 = "type4";



        /**
         * TESTS SYM CLASS
         */
        //Tests Sym Constructor
        Sym sym1 = new Sym(type1);
        Sym sym2 = new Sym(type2);
        Sym sym3 = new Sym(type3);
        Sym sym4 = new Sym(type4);
        
        //Test toString()
        if(sym1.getType()!=type1){
            System.out.println("getType for Sym does not return correct type");
        }


        

        /**
         * TESTS SymTable
         */
        
        //Testying SymTable Constructor
        SymTable symTable = new SymTable();
        
        //Makes sure that Empty SymTableException is thrown after empting the Symtable
        try{
            symTable.removeScope();
            if(symTable.symTable.size()==0){
                symTable.removeScope();
            }
        }catch( EmptySymTableException e){

        }
        //addScope
        symTable.addScope();
        int sizeOfSym = symTable.symTable.size() + 1; 
        if(symTable.symTable.size() != sizeOfSym){
            System.out.println("addScope does not add to size of symTable");
        }
        
        //addDecl
        //parameters are testName(string), and sym. (key value pairs of hashmap)
        //Confirms that addDecl increases size of symTable
        try{
            int expectedSize = symTable.symTable.size() + 6;
            symTable.addDecl(testName1, sym1);
            symTable.addDecl(testName2, sym2);
            symTable.addDecl(testName3, sym3);
            symTable.addDecl(testName2, sym3); 
            symTable.addDecl(testName4, sym4);
            symTable.addDecl(testName4, sym3); //to be removed
            if(symTable.symTable.size() != expectedSize ){
                System.out.println("addDecl does not increase size");
            }
        }catch(EmptySymTableException | IndexOutOfBoundsException | DuplicateSymException e){
            System.out.println("Exception thrown: addDec1 \n" + e.getLocalizedMessage());
        }

        //testing lookupLocal and lookupGlobal
        try{
            //Removal tests
            int expectedSize = symTable.symTable.size()-1;
            symTable.removeScope();
            if (symTable.symTable.size() != expectedSize) {
                System.out.println("removeScope does not remove from from front correctly");
            }

            //lookupLocal Tests

            //checks lookupLocal with name that doesnt exist in symTable.
            if(symTable.lookupLocal("Dylan") != null){
                System.out.println("lookUpLocal HashMap in the list does not handle not containing testName as a key");
            };

            //checks to make sure that when looking up local only the first hashmap is checked
            if(symTable.lookupLocal(testName1) != null){
                System.out.println("lookUpLocal checks more than the first and correct hashmap");
            };

            //checks to make sure when the first hashmap matches the name it returns asscociated symbol.
            if(symTable.lookupLocal(testName4) != sym4){
                System.out.println("lookUpLocal HashMap in the list does not check the first and correct hashmap");
            };


            //lookupGlobal Tests

            //checks to make sure that all hashmaps are checked when returning asscoiated symbol
            if(symTable.lookupGlobal(testName1) != sym1){
                System.out.println("lookUpGlobal does not check all hashmaps ");
            };
            if(symTable.lookupGlobal(testName2) != sym3) {
                System.out.println("lookupGlobal does not select closest to first when selecting associated symbols");
            }
            //testing if parameter does Not exist as key 
            if(symTable.lookupGlobal("Dylan") != null){
                System.out.println("lookUpLocal HashMap in the list does not handle not containing testName as a key");
            }

            }catch(EmptySymTableException e){
                if(!symTable.symTable.isEmpty()){
                    System.out.println("Erroneous Error thrown" + e.getLocalizedMessage());
            }
            }
        
    }
}
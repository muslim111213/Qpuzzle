package itam.q_puzzle.model;

import android.widget.Filter;

import java.util.ArrayList;

/**
 * Created by Hp on 3/17/2016.
 */
public class CustomFilter extends Filter {

    MyAdapter adapter;
    ArrayList<surah> filterList;


    public CustomFilter(ArrayList<surah> filterList, MyAdapter adapter)
    {
        this.adapter=adapter;
        this.filterList=filterList;

    }

    //FILTERING OCURS
    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results=new FilterResults();

        //CHECK CONSTRAINT VALIDITY
        if(constraint != null && constraint.length() > 0)
        {
            //CHANGE TO UPPER
            constraint=constraint.toString().toUpperCase();
            //STORE OUR FILTERED PLAYERS
            ArrayList<surah> filteredSurahs =new ArrayList<>();

            for (int i=0;i<filterList.size();i++)
            {
                //CHECK
                if(filterList.get(i).getName().toUpperCase().contains(constraint))
                {
                    //ADD PLAYER TO FILTERED PLAYERS
                    filteredSurahs.add(filterList.get(i));
                }
            }

            results.count= filteredSurahs.size();
            results.values= filteredSurahs;
        }else
        {
            results.count=filterList.size();
            results.values=filterList;

        }


        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {

       adapter.surahs = (ArrayList<surah>) results.values;

        //REFRESH
        adapter.notifyDataSetChanged();
    }
}

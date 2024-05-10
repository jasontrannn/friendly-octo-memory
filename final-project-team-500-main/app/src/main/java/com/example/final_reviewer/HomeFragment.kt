import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.final_reviewer.R
import com.example.final_reviewer.Search_RecyclerViewAdapter
import com.example.final_reviewer.YelpRestaurant
import java.util.ArrayList

class HomeFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: Search_RecyclerViewAdapter
    private var restaurantList: MutableList<YelpRestaurant> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_main, container, false)

        // Initialize RecyclerView
        recyclerView = view.findViewById(R.id.searchResults_recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Initialize adapter with empty list initially
        adapter = Search_RecyclerViewAdapter(restaurantList)
        recyclerView.adapter = adapter

        // Simulate loading of restaurant data (replace this with your actual data loading)
        loadRestaurantData()

        return view
    }

    // Method to load restaurant data (example data loading, replace with your implementation)
    private fun loadRestaurantData() {
        // Here you would load your list of YelpRestaurant items from wherever you get them
        // For example:
        // val fetchedRestaurantList = fetchDataFromAPI()
        // restaurantList.addAll(fetchedRestaurantList)

        // Simulated example data
//        val restaurant1 = YelpRestaurant("Restaurant 1", "123 Main St", "https://example.com/image1.jpg")
//        val restaurant2 = YelpRestaurant("Restaurant 2", "456 Oak St", "https://example.com/image2.jpg")
//        restaurantList.add(restaurant1)
//        restaurantList.add(restaurant2)

        // Notify the adapter that data set has changed
        adapter.notifyDataSetChanged()
    }
}
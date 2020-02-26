package studio.inprogress.postertestapp.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.android.synthetic.main.activity_posters.*
import studio.inprogress.postertestapp.App
import studio.inprogress.postertestapp.R
import studio.inprogress.postertestapp.di.PosterUiComponent
import studio.inprogress.postertestapp.model.dataSource.database.entity.PosterEntity
import studio.inprogress.postertestapp.ui.PosterListAdapter
import studio.inprogress.postertestapp.ui.itemDecorator.GridSpaceItemDecorator
import studio.inprogress.postertestapp.viewModel.PostersViewModel
import studio.inprogress.postertestapp.viewModel.Result
import studio.inprogress.postertestapp.viewModel.factory.PostersViewModelFactory
import javax.inject.Inject

class PostersActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {

    companion object {
        const val FILTER_YEAR_2020 = 2020
    }

    @Inject
    lateinit var postersViewModelFactory: PostersViewModelFactory
    private lateinit var postersViewModel: PostersViewModel
    private val postersAdapter = PosterListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_posters)
        App.getComponentStorage().getOrCreateComponent<PosterUiComponent>().inject(this)
        initUI()
        postersViewModel = ViewModelProvider(this, postersViewModelFactory)[PostersViewModel::class.java]
        subscribeToLiveData()
    }

    private fun initUI() {
        initRefreshLayout()
        initRecyclerView()
        initPosterFilterSwitch()
    }

    private fun initPosterFilterSwitch() {
        postersFilterSwitch.setOnCheckedChangeListener { _, isChecked ->
            postersViewModel.setYearFilter(if (isChecked) FILTER_YEAR_2020 else null)
            postersRecyclerView.post {
                postersRecyclerView.smoothScrollToPosition(0)
            }
        }
    }

    private fun initRecyclerView() {
        val colCount = resources.getInteger(R.integer.posters_table_col_count)
        postersRecyclerView.layoutManager =
            GridLayoutManager(this, colCount).apply {
                this.spanSizeLookup
            }
        postersRecyclerView.adapter = postersAdapter
        postersRecyclerView.addItemDecoration(
            GridSpaceItemDecorator(
                resources.getDimension(R.dimen.poster_table_col_space).toInt()
            )
        )
    }

    private fun initRefreshLayout() {
        refreshLayout.setOnRefreshListener(this)
    }

    private fun subscribeToLiveData() {
        postersViewModel.observePosterList(this, Observer { result ->
            when (result) {
                Result.Empty -> showEmptyPosterList()
                is Result.Success -> showPosterList(result.data)
                is Result.Error -> showError(result.throwable)
            }
        })
        postersViewModel.observePosterLoadingProgress(this, Observer { inProgress ->
            toggleProgress(inProgress)
        })
    }

    private fun toggleProgress(inProgress: Boolean) {
        refreshLayout.isRefreshing = inProgress
    }

    private fun showPosterList(posterList: List<PosterEntity>) {
        refreshLayout.isRefreshing = false
        postersAdapter.updateList(posterList)
    }

    private fun showEmptyPosterList() {
        refreshLayout.isRefreshing = false
        postersAdapter.setPosterList(emptyList())
        postersAdapter.notifyDataSetChanged()
    }

    private fun showError(throwable: Throwable) {
        refreshLayout.isRefreshing = false
        Toast.makeText(this, throwable.message, Toast.LENGTH_SHORT).show()
    }

    override fun onRefresh() {
        postersViewModel.loadAllPosters()
    }
}